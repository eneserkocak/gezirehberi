package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoriteMosque")
data class FavoriteMosque(
    val favoriteMosqueId: Int = 0,
    val favoriteMosqueName: String ="",
    val favoriteMosqueLocation:String="",
    var favoriteMosqueLat: Double = 0.0,
    var favoriteMosqueLong: Double = 0.0,


    ) {
    @PrimaryKey(autoGenerate = true)
    var id=0

}