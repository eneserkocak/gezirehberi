package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.*

@Dao
interface FavoriteCaravanseraiDao{

    @Query("SELECT*FROM favoriteCaravanserai")
    fun getAll():  List<FavoriteCaravanserai>

    @Insert
    fun insertAll(vararg favoriteCaravanserai: FavoriteCaravanserai)

    @Update
    fun update(favoriteCaravanserai: FavoriteCaravanserai)

    @Delete
    fun delete(favoriteCaravanserai: FavoriteCaravanserai):Int




}