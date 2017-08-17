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
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.criteria.Predicate

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

    override fun isVideoExists(id: String) = videoRepository.exists(id)

    override fun getAllStatsInfoByVideoId(videoId: String): List<StatInfoGet> {
        return ArrayList<StatInfoGet>(videoRepository.findOne(videoId).reports.map { stat ->
            modelMapper.map(stat, StatInfoGet::class.java)
        })
    }

    override fun isVideoValid(v: String) = !isVideoExists(v) && youtube.isVideoExists(v)

    override fun addVideo(v: VideoPost) {
        val video: Video? = modelMapper.map(v, Video::class.java)
        videoRepository.saveAndFlush(video)
    }

    override fun getAllStatsInfo(id: String?, from: Long?, offset: Long?, to: Long?): List<StatInfoGet> {
        return ArrayList<StatInfoGet>(reportRepository.findAll().map { stat ->
            modelMapper.map(stat, StatInfoGet::class.java)
        })
    }

    override fun getFilteredStatsInfo(list: List<StatInfoGet>, from: Long?, offset: Long?, to: Long?): List<StatInfoGet> {
        return list.filter { it ->
            val fromPredicate: Boolean = if (from == null) true else it.time >= from
            val toPredicate: Boolean = if (to == null) true else it.time <= to
            fromPredicate && toPredicate
        }
    }

    override fun getVideo(id: String): VideoGet? {
        return modelMapper.map(videoRepository.findOne(id), VideoGet::class.java)
//        val builder = em.criteriaBuilder
//        val query = builder.createQuery(Video::class.java)
//        val root = query.from(Video::class.java)
//
//        val array = arrayListOf<Predicate>()
//
//        array.add(builder.equal(root.get<String>("id"), id))
//        val join = root.join<Video, Report>("reports")
//        if (from != null) array.add(builder.greaterThanOrEqualTo(join.get<Long>("time"), from))
//        if (to != null) array.add(builder.lessThanOrEqualTo(join.get<Long>("time"), to))
//        query.where(builder.and(*array.toTypedArray()))
//        return try {
//            val response = modelMapper.map(em.createQuery(query.select(root)).singleResult, VideoGet::class.java)
//            val coeff = offset?.div(60000.toFloat())
//            if (offset == null || coeff!! < 1) {
//                response
//            } else {
//                throw Exception()
//            }
//        } catch (e: Exception) {
//            null
//        }
    }

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
