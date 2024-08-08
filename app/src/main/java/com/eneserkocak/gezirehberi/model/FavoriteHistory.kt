package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteHistory")
 data class FavoriteHistory(
    val favoriteHistoryId: Int = 0,
    val favoriteHistoryName: String ="",
    val favoriteHistoryLocation:String="",
    var favoriteHistoryLat: Double = 0.0,
    var favoriteHistoryLong: Double = 0.0,


    ) {
    @PrimaryKey(autoGenerate = true)
    var id=0
}