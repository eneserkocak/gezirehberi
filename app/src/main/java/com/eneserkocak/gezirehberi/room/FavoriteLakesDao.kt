package com.eneserkocak.gezirehberi.room

import androidx.room.*
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoritePark

@Dao
interface FavoriteLakesDao{

    @Query("SELECT*FROM favoriteLake")
    fun getAll():  List<FavoriteLake>

    @Insert
    fun insertAll(vararg favoriteLake: FavoriteLake)

    @Update
    fun update(favoriteLake: FavoriteLake)

    @Delete
    fun delete(favoriteLake: FavoriteLake):Int




}