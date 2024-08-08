package com.eneserkocak.gezirehberi.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil

import com.eneserkocak.gezirehberi.databinding.RowFavoritelakeBinding
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.FavoritePark
import com.eneserkocak.gezirehberi.room.FavoriteLakesDatabase
import com.eneserkocak.gezirehberi.room.FavoriteParksDatabase

class FavoriteLakeListAdapter(val selectedFavoriteLake: (FavoriteLake)->Unit):RecyclerView.Adapter<FavoriteLakeListAdapter.FavoriteLakeListViewHolder>() {

    val favoriteList= arrayListOf<FavoriteLake>()

    class FavoriteLakeListViewHolder(val binding:RowFavoritelakeBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteLakeListViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoritelakeBinding>(inflater, R.layout.row_favoritelake, parent,false)
        return FavoriteLakeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteLakeListViewHolder, position: Int) {

            val favoriteLake= favoriteList[position]
            holder.binding.favoriteLakeList= favoriteLake

        AppUtil.pictureFavoriteLake(favoriteLake,holder.itemView.context,holder.binding.favoriteLakeImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoriteLake.invoke(favoriteLake)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
            Navigation.findNavController(it).popBackStack()
            Navigation.findNavController(it).navigate(R.id.lakeMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
            deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateFavoriteLakeList(newLakeList: List<FavoriteLake>) {

        favoriteList.clear()
        favoriteList.addAll(newLakeList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context, position:Int, view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteLakesDatabase.getInstance(context)?.favoriteLakesDao()?.delete(favoriteList[position])

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