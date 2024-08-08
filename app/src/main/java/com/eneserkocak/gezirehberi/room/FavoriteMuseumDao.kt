package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoriteHistory
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoriteMuseum
import com.eneserkocak.gezirehberi.model.FavoritePark

@Dao
interface FavoriteMuseumDao{

    @Query("SELECT*FROM favoriteMuseum")
    fun getAll():  List<FavoriteMuseum>

    @Insert
    fun insertAll(vararg favoriteMuseum: FavoriteMuseum)

    @Update
    fun update(favoriteMuseum: FavoriteMuseum)

    @Delete
    fun delete(favoriteMuseum: FavoriteMuseum):Int




}