package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Dao
interface FavoriteMosqueDao{

    @Query("SELECT*FROM favoriteMosque")
    fun getAll():  List<FavoriteMosque>

    @Insert
    fun insertAll(vararg favoriteMosque: FavoriteMosque)

    @Update
    fun update(favoriteMosque: FavoriteMosque)

    @Delete
    fun delete(favoriteMosque: FavoriteMosque):Int




}