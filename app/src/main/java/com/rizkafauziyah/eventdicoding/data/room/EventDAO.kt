package com.rizkafauziyah.eventdicoding.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rizkafauziyah.eventdicoding.data.database.entity.DetailDataEntity

@Dao
interface EventDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(detail: DetailDataEntity)

    @Delete
    suspend fun delete(detail: DetailDataEntity)

    @Query("SELECT * FROM detaildata")
    fun getAllEvents(): LiveData<List<DetailDataEntity>>
}