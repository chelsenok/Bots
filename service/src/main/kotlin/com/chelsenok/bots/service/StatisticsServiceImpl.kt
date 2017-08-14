package com.chelsenok.bots.service

import com.chelsenok.bots.repository.ReportRepository
import com.chelsenok.bots.repository.VideoRepository
import com.chelsenok.bots.repository.entities.Report
import com.chelsenok.bots.repository.entities.Video
import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoPost
import com.chelsenok.youtube.YouTube
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.criteria.JoinType
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

    override fun getIdByFilter(videoId: String?, likeCount: Long?, dislikeCount: Long?): List<Long> {
        val builder = em.criteriaBuilder
        val query = builder.createQuery(Report::class.java)
        val root = query.from(Report::class.java)

        val array = arrayListOf<Predicate>()

        if (videoId != null) array.add(builder.equal(root.get<String>("videoId"), videoId))
        if (likeCount != null) array.add(builder.equal(root.get<Long>("likeCount"), likeCount))
        if (dislikeCount != null) array.add(builder.equal(root.get<Long>("dislikeCount"), dislikeCount))

        query.where(builder.and(*array.toTypedArray()))
        return em.createQuery(query.select(root)).resultList.map { it -> it.id }
    }

    override fun getStatusByFilter(videoId: String?, likeCount: Long?): Boolean {
        val builder = em.criteriaBuilder
        val query = builder.createQuery(Video::class.java)
        val root = query.from(Video::class.java)

        val array = arrayListOf<Predicate>()
        val video = videoRepository.findOne(videoId)

        if (video != null) {
            array.add(builder.equal(
                    root.joinSet<Video, Report>("reports", JoinType.INNER).get<Long>("likeCount"), likeCount
            ))
        }

        query.where(builder.and(*array.toTypedArray()))
        return !em.createQuery(query.select(root)).resultList.isEmpty()
    }

}
