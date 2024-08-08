package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoriteHistory
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoritePark

@Dao
interface FavoriteHistoryDao{

    @Query("SELECT*FROM favoriteHistory")
    fun getAll():  List<FavoriteHistory>

    @Insert
    fun insertAll(vararg favoriteHistory: FavoriteHistory)

    @Update
    fun update(favoriteHistory: FavoriteHistory)

    @Delete
    fun delete(favoriteHistory: FavoriteHistory):Int




}