package com.eneserkocak.gezirehberi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoriteCaravanserai")
class FavoriteCaravanserai(

    val favoriteCaravanseraiId: Int = 0,
    val favoriteCaravanseraiName: String ="",
    val favoriteCaravanseraiLocation:String="",
    var favoriteCaravanseraiLat: Double = 0.0,
    var favoriteCaravanseraiLong: Double = 0.0,


    ) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
