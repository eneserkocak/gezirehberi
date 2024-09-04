package com.eneserkocak.gezirehberi.model

data class Park
    (

   var id: Int = 0,
   var yerAdi: String ="",
   var aciklama: String="",
   var ilce:String="",
   var lat: Double = 0.0,
   var long: Double = 0.0,
   var web: String="",
   var open: String="",
   var close: String=""
    ):java.io.Serializable {
}