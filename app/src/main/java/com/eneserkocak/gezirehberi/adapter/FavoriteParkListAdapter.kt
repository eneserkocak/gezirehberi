package com.eneserkocak.gezirehberi.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowFavoriteparkBinding
import com.eneserkocak.gezirehberi.model.FavoritePark
import com.eneserkocak.gezirehberi.room.FavoriteParksDatabase

class FavoriteParkListAdapter(val selectedFavoritePark: (FavoritePark)->Unit):RecyclerView.Adapter<FavoriteParkListAdapter.FavoriteListViewHolder>() {

    val favoriteList= arrayListOf<FavoritePark>()

    class FavoriteListViewHolder(val binding:RowFavoriteparkBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoriteparkBinding>(inflater, R.layout.row_favoritepark, parent,false)
        return FavoriteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {

        val favoritePark= favoriteList[position]
        holder.binding.favoriteParkList= favoritePark

        AppUtil.gorselAl(favoritePark,holder.itemView.context,holder.binding.favoriteParkImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoritePark.invoke(favoritePark)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")

            findNavController(it).popBackStack()
            findNavController(it).navigate(R.id.parkMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
                deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }

    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateFavoriteParkingList(newParkList: List<FavoritePark>) {

        favoriteList.clear()
        favoriteList.addAll(newParkList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context,position:Int,view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteParksDatabase.getInstance(context)?.favoriteParksDao()?.delete(favoriteList[position])

                favoriteList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, favoriteList.size)
                AppUtil.longToast(context,"Favori Listemden Silindi..")

                Navigation.findNavController(view).popBackStack()
                Navigation.findNavController(view).navigate(R.id.favoriteFragment)

            }
        })


        alert.setNegativeButton("HAYIR", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // findNavController().popBackStack()
            }
        })

        alert.show()


    }
}