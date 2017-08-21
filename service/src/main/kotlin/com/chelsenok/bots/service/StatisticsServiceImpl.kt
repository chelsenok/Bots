package com.chelsenok.bots.service

import com.chelsenok.bots.repository.ReportRepository
import com.chelsenok.bots.repository.VideoRepository
import com.chelsenok.bots.repository.entities.Report
import com.chelsenok.bots.repository.entities.Video
import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoGet
import com.chelsenok.bots.service.dtos.VideoPost
import com.chelsenok.youtube.YouTube
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Suppress("UNUSED_EXPRESSION")
@Service
class StatisticsServiceImpl : StatisticsService {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var videoRepository: VideoRepository

    @Autowired
    private lateinit var modelMapper: ModelMapper

    @Autowired
    private lateinit var youtube: YouTube

    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    @Qualifier("fixedRate")
    private var fixedRate: Long = 0

    override fun isVideoExists(id: String) = videoRepository.exists(id)

    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet> {
        return videoRepository.findOne(videoId).reports.map { stat ->
            modelMapper.map(stat, StatInfoGet::class.java)
        }
    }

    override fun isVideoValid(v: String) = !isVideoExists(v) && youtube.isVideoExists(v)

    override fun addVideo(v: VideoPost) {
        val video: Video? = modelMapper.map(v, Video::class.java)
        videoRepository.saveAndFlush(video)
    }

    override fun getAllStatsInfo(ids: ArrayList<String>?,
                                 from: Long?,
                                 offset: Long?,
                                 to: Long?
    ): List<StatInfoGet> {
        val builder = em.criteriaBuilder
        val query = builder.createQuery(Report::class.java)
        val root = query.from(Report::class.java)

        val array = arrayListOf<Predicate>()
        if (from != null) array.add(builder.greaterThanOrEqualTo(root.get<Long>("time"), from))
        if (to != null) array.add(builder.lessThanOrEqualTo(root.get<Long>("time"), to))

        return if (ids != null) {
            val separatedStats = Array(ids.size, { i ->
                val idArray = arrayListOf(*array.toTypedArray())
                idArray.add(builder.equal(root.get<String>("videoId"), ids[i]))
                getFilteredStatsByPredicates(query, builder, idArray, root, offset)
            })
            separatedStats.asList().stream()
                    .flatMap(List<StatInfoGet>::stream)
                    .collect(Collectors.toList())
        } else {
            getFilteredStatsByPredicates(query, builder, array, root, offset)
        }
    }

    private fun getFilteredStatsByPredicates(
            query: CriteriaQuery<Report>,
            builder: CriteriaBuilder,
            array: ArrayList<Predicate>,
            root: Root<Report>,
            offset: Long?
    ): List<StatInfoGet> {
        query.where(builder.and(*array.toTypedArray()))
        val response = em.createQuery(query.select(root)).resultList.map { it ->
            modelMapper.map(it, StatInfoGet::class.java)
        }
        return if (offset == null) response else filterListByOffset(response, fixedRate, offset)
    }

    override fun getFilteredStatsInfo(
            list: List<StatInfoGet>,
            from: Long?,
            offset: Long?,
            to: Long?
    ): List<StatInfoGet> {
        val response = list.filter { it ->
            val array = booleanArrayOf(
                    if (from == null) true else it.time >= from,
                    if (to == null) true else it.time <= to
            )
            !array.contains(false)
        }
        return if (offset == null) response else filterListByOffset(response, fixedRate, offset)
    }

    private fun <T> filterListByOffset(list: List<T>, fixedRate: Long, offset: Long): List<T> {
        val coeff = offset.div(fixedRate)
        var count: Long = 0
        return list.filter {
            val status = if (coeff == 0.toLong()) true else count % coeff == 0.toLong()
            count++
            status
        }
    }

    override fun getVideo(id: String): VideoGet? =
            modelMapper.map(videoRepository.findOne(id), VideoGet::class.java)

    override fun getIdsByFilter(videoId: String, likeCount: Long?, dislikeCount: Long?): List<Long> {
        val builder = em.criteriaBuilder
        val query = builder.createQuery(Report::class.java)
        val root = query.from(Report::class.java)

        val array = arrayListOf<Predicate>()

        array.add(builder.equal(root.get<String>("videoId"), videoId))
        if (likeCount != null) array.add(builder.equal(root.get<Long>("likeCount"), likeCount))
        if (dislikeCount != null) array.add(builder.equal(root.get<Long>("dislikeCount"), dislikeCount))

        query.where(builder.and(*array.toTypedArray()))
        return em.createQuery(query.select(root)).resultList.map { it -> it.id }
    }

    override fun getExistByFilter(videoId: String, likeCount: Long?, commentCount: Long?): Boolean {
        val builder = em.criteriaBuilder
        val query = builder.createQuery(Video::class.java)
        val root = query.from(Video::class.java)

        val array = arrayListOf<Predicate>()
        val join = root.join<Video, Report>("reports")

        array.add(builder.equal(
                join.get<Long>("likeCount"), likeCount
        ))
        array.add(builder.equal(
                join.get<Long>("commentCount"), commentCount
        ))

        query.where(builder.and(*array.toTypedArray()))
        return !em.createQuery(query.select(root)).resultList.isEmpty()
    }

}
