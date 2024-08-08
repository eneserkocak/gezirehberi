package com.eneserkocak.gezirehberi.room

import android.content.Context
import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoriteMosque
import com.eneserkocak.gezirehberi.model.FavoriteMuseum
import com.eneserkocak.gezirehberi.model.FavoritePark

@Database(entities = [FavoriteMosque::class], version = 1)
abstract class FavoriteMosqueDatabase : RoomDatabase() {
    abstract fun favoriteMosqueDao(): FavoriteMosqueDao

    companion object {
        var INSTANCE: FavoriteMosqueDatabase?=null

        fun getInstance(context: Context): FavoriteMosqueDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteMosqueDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteMosqueDatabase::class.java, "usermosque.db").allowMainThreadQueries().fallbackToDestructiveMigration()
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