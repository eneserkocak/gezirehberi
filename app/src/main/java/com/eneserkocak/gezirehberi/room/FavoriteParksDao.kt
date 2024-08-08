package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoritePark

@Dao
interface FavoriteParksDao{

    @Query("SELECT*FROM favoritePark")
    fun getAll():  List<FavoritePark>

    @Insert
    fun insertAll(vararg favoritePark: FavoritePark)

    @Update
    fun update(favoritePark: FavoritePark)

    @Delete
    fun delete(favoritePark: FavoritePark):Int




}