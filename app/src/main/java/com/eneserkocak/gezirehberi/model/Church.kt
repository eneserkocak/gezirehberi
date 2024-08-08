package com.eneserkocak.gezirehberi.model

class Church(


    var id: Int = 0,
    var placeName: String ="",
    var explanation: String="",
    var district:String="",
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var web: String="",
    var open: String="",
    var close: String=""
):java.io.Serializable {
}