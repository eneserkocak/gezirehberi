package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoriteBath")
class FavoriteBath (
    val favoriteBathId: Int = 0,
    val favoriteBathName: String ="",
    val favoriteBathLocation:String="",
    var favoriteBathLat: Double = 0.0,
    var favoriteBathLong: Double = 0.0,


    ) {
    @PrimaryKey(autoGenerate = true)
    var id=0

}