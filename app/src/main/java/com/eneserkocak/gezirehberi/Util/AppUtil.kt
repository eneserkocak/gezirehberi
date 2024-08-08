package com.eneserkocak.gezirehberi.Util

import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.eneserkocak.gezirehberi.model.*
import com.google.firebase.storage.FirebaseStorage

object AppUtil {

    fun longToast(context: Context?,message:String){
        context?.let {
           Toast.makeText(it,message,Toast.LENGTH_LONG).show()
    }

}
    fun shortToast(context: Context?,message:String){
         context?.let {
            Toast.makeText(it,message,Toast.LENGTH_SHORT).show()
    }

}

    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    }


    fun gorselIndir(park: Park,context: Context,imageView: ImageView){
        val dosyaIsmi= "${park.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun gorselAl(favoritePark: FavoritePark,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoritePark.favoriteParkId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun gorselLake(lake: Lake,context: Context,imageView: ImageView){
        val dosyaIsmi= "${lake.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteLake(favoriteLake: FavoriteLake,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteLake.favoriteLakeId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureHistory(history: History,context: Context,imageView: ImageView){
        val dosyaIsmi= "${history.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteHistory(favoriteHistory: FavoriteHistory,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteHistory.favoriteHistoryId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }


    fun pictureMuseum(museum: Museum,context: Context,imageView: ImageView){
        val dosyaIsmi= "${museum.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteMuseum(favoriteMuseum: FavoriteMuseum,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteMuseum.favoriteMuseumId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }


    fun pictureMosque(mosque: Mosque,context: Context,imageView: ImageView){
        val dosyaIsmi= "${mosque.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteMosque(favoriteMosque: FavoriteMosque,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteMosque.favoriteMosqueId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureAncintcity(ancientcity: Ancientcity,context: Context,imageView: ImageView){
        val dosyaIsmi= "${ancientcity.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteAncientcity(favoriteAncientcity: FavoriteAncientcity,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteAncientcity.favoriteAncientcityId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureCaravanserai(caravanserai: Caravanserai,context: Context,imageView: ImageView){
        val dosyaIsmi= "${caravanserai.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteCaravanserai(favoriteCaravanserai: FavoriteCaravanserai,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteCaravanserai.favoriteCaravanseraiId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }


    fun pictureBath(bath: Bath,context: Context,imageView: ImageView){
        val dosyaIsmi= "${bath.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteBath(favoriteBath: FavoriteBath,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteBath.favoriteBathId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }



    fun pictureChurch(church: Church,context: Context,imageView: ImageView){
        val dosyaIsmi= "${church.id}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }

    fun pictureFavoriteChurch(favoriteChurch: FavoriteChurch,context: Context,imageView: ImageView){
        val dosyaIsmi= "${favoriteChurch.favoriteChurchId}.jpg"
        //İLAÇ GÖRSELLERİNİ FİREBASE DEN ÇEK
        val storageRef = FirebaseStorage.getInstance().reference.child(dosyaIsmi)
        storageRef.downloadUrl.addOnSuccessListener {
            Glide.with(context)
                .load(it)
                .into(imageView)
        }
            .addOnFailureListener { e->
                println(e)

            }
    }




}