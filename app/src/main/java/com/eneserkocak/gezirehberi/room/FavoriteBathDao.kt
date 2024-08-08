package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Dao
interface FavoriteBathDao{

    @Query("SELECT*FROM favoriteBath")
    fun getAll():  List<FavoriteBath>

    @Insert
    fun insertAll(vararg favoriteBath: FavoriteBath)

    @Update
    fun update(favoriteBath: FavoriteBath)

    @Delete
    fun delete(favoriteBath: FavoriteBath):Int




}