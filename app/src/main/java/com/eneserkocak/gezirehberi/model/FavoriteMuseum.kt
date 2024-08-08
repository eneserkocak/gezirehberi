package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

 @Entity (tableName = "favoriteMuseum")
 data class FavoriteMuseum(

    val favoriteMuseumId: Int = 0,
    val favoriteMuseumName: String ="",
    val favoriteMuseumLocation:String="",
    var favoriteMuseumLat: Double = 0.0,
    var favoriteMuseumLong: Double = 0.0,


    ) {
    @PrimaryKey(autoGenerate = true)
    var id=0

}


