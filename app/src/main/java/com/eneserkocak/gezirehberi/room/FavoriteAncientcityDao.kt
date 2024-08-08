package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Dao
interface FavoriteAncientcityDao{

    @Query("SELECT*FROM favoriteAncintcity")
    fun getAll():  List<FavoriteAncientcity>

    @Insert
    fun insertAll(vararg favoriteAncientcity: FavoriteAncientcity)

    @Update
    fun update(favoriteAncientcity: FavoriteAncientcity)

    @Delete
    fun delete(favoriteAncientcity: FavoriteAncientcity):Int




}