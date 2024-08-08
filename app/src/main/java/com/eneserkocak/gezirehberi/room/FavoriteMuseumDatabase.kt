package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoriteMuseum
import com.eneserkocak.gezirehberi.model.FavoritePark

@Database(entities = [FavoriteMuseum::class], version = 1)
abstract class FavoriteMuseumDatabase : RoomDatabase() {
    abstract fun favoriteMuseumDao(): FavoriteMuseumDao

    companion object {
        var INSTANCE: FavoriteMuseumDatabase?=null

        fun getInstance(context: Context): FavoriteMuseumDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteMuseumDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteMuseumDatabase::class.java, "usermuseum.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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