package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteLake")
 data class FavoriteLake (

    val favoriteLakeId: Int = 0,
    val favoriteLakeName: String ="",
    val favoriteLakeLocation:String="",
    var favoriteLakeLat: Double = 0.0,
    var favoriteLakeLong: Double = 0.0,


    ) {
        @PrimaryKey(autoGenerate = true)
        var id=0

}