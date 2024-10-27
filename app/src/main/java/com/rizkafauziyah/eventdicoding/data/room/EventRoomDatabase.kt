package com.rizkafauziyah.eventdicoding.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizkafauziyah.eventdicoding.data.database.entity.DetailDataEntity
import com.rizkafauziyah.eventdicoding.data.room.EventDAO


@Database(entities = [DetailDataEntity::class], version = 2)
abstract class EventRoomDatabase : RoomDatabase() {
    abstract fun eventDAO(): EventDAO

    companion object {
        @Volatile
        private var INSTANCE: EventRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): EventRoomDatabase {
            if (INSTANCE == null) {
                synchronized(EventRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        EventRoomDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as EventRoomDatabase
        }
    }
}