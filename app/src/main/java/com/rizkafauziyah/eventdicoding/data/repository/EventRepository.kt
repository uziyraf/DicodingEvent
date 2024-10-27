package com.rizkafauziyah.eventdicoding.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.rizkafauziyah.eventdicoding.data.database.entity.DetailDataEntity
import com.rizkafauziyah.eventdicoding.data.database.room.EventRoomDatabase
import com.rizkafauziyah.eventdicoding.data.room.EventDAO
import com.rizkafauziyah.eventdicoding.model.DetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepository(application: Application) {
    private val mEventDao: EventDAO

    init {
        val db = EventRoomDatabase.getDatabase(application)
        mEventDao = db.eventDAO()  // Pastikan eventDAO diambil dari EventRoomDatabase
    }

    fun getAllEvent(): LiveData<List<DetailDataEntity>> = mEventDao.getAllEvents()

    suspend fun insert(detailData: DetailDataEntity) {
        withContext(Dispatchers.IO) {
            mEventDao.run { insert(detailData) }
        }
    }

    suspend fun delete(detailData: DetailDataEntity) {
        withContext(Dispatchers.IO) {
            mEventDao.run { delete(detailData) }
        }
    }

    fun mapDetailDataToEntity(detailData: DetailData): DetailDataEntity {
        return DetailDataEntity(
            id = detailData.id,
            name = detailData.name,
            summary = detailData.summary,
            description = detailData.description,
            imageLogo = detailData.imageLogo,
            category = detailData.category,
            cityName = detailData.cityName,
            mediaCover = detailData.mediaCover,
            beginTime = detailData.beginTime,
            registrants = detailData.registrants,
            endTime = detailData.endTime,
            link = detailData.link
        )
    }
}


