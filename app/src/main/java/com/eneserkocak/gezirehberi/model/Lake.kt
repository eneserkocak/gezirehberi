package com.eneserkocak.gezirehberi.model

 data class Lake(
    var id: Int = 0,
    var placeName: String ="",
    var explanation: String="",
    var district:String="",
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var web: String="",
    var closing: String=""
):java.io.Serializable{
}