package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoriteChurch")
class FavoriteChurch (
    val favoriteChurchId: Int = 0,
    val favoriteChurchName: String ="",
    val favoriteChurchLocation:String="",
    var favoriteChurchLat: Double = 0.0,
    var favoriteChurchLong: Double = 0.0,


    ) {
    @PrimaryKey(autoGenerate = true)
    var id=0

}