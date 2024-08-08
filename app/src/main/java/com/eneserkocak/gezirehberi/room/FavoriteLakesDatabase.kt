package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoritePark

@Database(entities = [FavoriteLake::class], version = 1)
abstract class FavoriteLakesDatabase : RoomDatabase() {
    abstract fun favoriteLakesDao(): FavoriteLakesDao

    companion object {
        var INSTANCE: FavoriteLakesDatabase?=null

        fun getInstance(context: Context): FavoriteLakesDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteLakesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteLakesDatabase::class.java, "users.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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