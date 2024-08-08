package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoriteAncintcity")
data class FavoriteAncientcity(
    val favoriteAncientcityId: Int = 0,
    val favoriteAncientcityName: String ="",
    val favoriteAncientcityLocation:String="",
    var favoriteAncientcityLat: Double = 0.0,
    var favoriteAncientcityLong: Double = 0.0,


    ) {
    @PrimaryKey(autoGenerate = true)
    var id=0

  }