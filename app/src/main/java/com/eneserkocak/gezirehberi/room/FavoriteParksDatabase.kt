package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoritePark

@Database(entities = [FavoritePark::class], version = 4)
abstract class FavoriteParksDatabase : RoomDatabase() {
    abstract fun favoriteParksDao(): FavoriteParksDao

    companion object {
        var INSTANCE: FavoriteParksDatabase?=null

        fun getInstance(context: Context): FavoriteParksDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteParksDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteParksDatabase::class.java, "user.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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