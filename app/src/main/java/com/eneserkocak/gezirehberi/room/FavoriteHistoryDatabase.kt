package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoriteHistory
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoritePark

@Database(entities = [FavoriteHistory::class], version = 1)
abstract class FavoriteHistoryDatabase : RoomDatabase() {
    abstract fun favoriteHistoryDao(): FavoriteHistoryDao

    companion object {
        var INSTANCE: FavoriteHistoryDatabase?=null

        fun getInstance(context: Context): FavoriteHistoryDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteHistoryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteHistoryDatabase::class.java, "userh.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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