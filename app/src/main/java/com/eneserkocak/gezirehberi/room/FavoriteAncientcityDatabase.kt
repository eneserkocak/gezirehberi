package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Database(entities = [FavoriteAncientcity::class], version = 1)
abstract class FavoriteAncientcityDatabase : RoomDatabase() {
    abstract fun favoriteAncientcityDao(): FavoriteAncientcityDao

    companion object {
        var INSTANCE: FavoriteAncientcityDatabase?=null

        fun getInstance(context: Context): FavoriteAncientcityDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteAncientcityDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteAncientcityDatabase::class.java, "userancientcity.db").allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}