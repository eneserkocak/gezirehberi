package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Database(entities = [FavoriteCaravanserai::class], version = 1)
abstract class FavoriteCaravanseraiDatabase : RoomDatabase() {
    abstract fun favoriteCaravanseraiDao(): FavoriteCaravanseraiDao

    companion object {
        var INSTANCE: FavoriteCaravanseraiDatabase?=null

        fun getInstance(context: Context): FavoriteCaravanseraiDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteCaravanseraiDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteCaravanseraiDatabase::class.java, "usercaravanserai.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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