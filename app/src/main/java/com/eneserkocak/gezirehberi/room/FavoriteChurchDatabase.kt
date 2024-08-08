package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Database(entities = [FavoriteChurch::class], version = 1)
abstract class FavoriteChurchDatabase : RoomDatabase() {
    abstract fun favoriteChurchDao(): FavoriteChurchDao

    companion object {
        var INSTANCE: FavoriteChurchDatabase?=null

        fun getInstance(context: Context): FavoriteChurchDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteChurchDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteChurchDatabase::class.java, "userchurch.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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