package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "favoritePark")
data class FavoritePark(

    val favoriteParkId: Int = 0,
    val favoriteParkName: String ="",
    val favoriteParkLocation:String="",
    var favoriteParkLat: Double = 0.0,
    var favoriteParkLong: Double = 0.0,


) {
    @PrimaryKey(autoGenerate = true)
    var id=0
}