package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Dao
interface FavoriteChurchDao{

    @Query("SELECT*FROM favoriteChurch")
    fun getAll():  List<FavoriteChurch>

    @Insert
    fun insertAll(vararg favoriteChurch: FavoriteChurch)

    @Update
    fun update(favoriteChurch: FavoriteChurch)

    @Delete
    fun delete(favoriteChurch: FavoriteChurch):Int




}