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
import com.eneserkocak.gezirehberi.databinding.RowFavoritehistoryBinding
import com.eneserkocak.gezirehberi.databinding.RowFavoritemuseumBinding
import com.eneserkocak.gezirehberi.model.FavoriteHistory
import com.eneserkocak.gezirehberi.model.FavoriteMuseum
import com.eneserkocak.gezirehberi.room.FavoriteHistoryDatabase
import com.eneserkocak.gezirehberi.room.FavoriteMuseumDatabase

class FavoriteMuseumListAdapter(val selectedFavoriteMuseum: (FavoriteMuseum)->Unit): RecyclerView.Adapter<FavoriteMuseumListAdapter.FavoriteMuseumViewHolder>() {

    val favoriteMuseumPlaceList= arrayListOf<FavoriteMuseum>()

    class FavoriteMuseumViewHolder(val binding:RowFavoritemuseumBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMuseumViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoritemuseumBinding>(inflater, R.layout.row_favoritemuseum, parent,false)
        return FavoriteMuseumListAdapter.FavoriteMuseumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMuseumViewHolder, position: Int) {

        val favoriteMuseum= favoriteMuseumPlaceList[position]
        holder.binding.favoriteMuseumList= favoriteMuseum

        AppUtil.pictureFavoriteMuseum(favoriteMuseum,holder.itemView.context,holder.binding.favoriteMuseumImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoriteMuseum.invoke(favoriteMuseum)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
            Navigation.findNavController(it).popBackStack()
            Navigation.findNavController(it).navigate(R.id.museumMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
            deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }
    }

    override fun getItemCount(): Int {
        return favoriteMuseumPlaceList.size
    }

    fun updateFavoriteMuseumList(newMuseumList: List<FavoriteMuseum>) {

        favoriteMuseumPlaceList.clear()
        favoriteMuseumPlaceList.addAll(newMuseumList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context, position:Int, view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteMuseumDatabase.getInstance(context)?.favoriteMuseumDao()?.delete(favoriteMuseumPlaceList[position])

                favoriteMuseumPlaceList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, favoriteMuseumPlaceList.size)
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