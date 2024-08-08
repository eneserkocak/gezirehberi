package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Database(entities = [FavoriteBath::class], version = 1)
abstract class FavoriteBathDatabase : RoomDatabase() {
    abstract fun favoriteBathDao(): FavoriteBathDao

    companion object {
        var INSTANCE: FavoriteBathDatabase?=null

        fun getInstance(context: Context): FavoriteBathDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteBathDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteBathDatabase::class.java, "userbath.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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