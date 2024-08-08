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
import com.eneserkocak.gezirehberi.databinding.RowFavoritemosqueBinding
import com.eneserkocak.gezirehberi.databinding.RowFavoritemuseumBinding
import com.eneserkocak.gezirehberi.model.FavoriteMosque
import com.eneserkocak.gezirehberi.model.FavoriteMuseum
import com.eneserkocak.gezirehberi.model.Mosque
import com.eneserkocak.gezirehberi.room.FavoriteMosqueDatabase
import com.eneserkocak.gezirehberi.room.FavoriteMuseumDatabase

class FavoriteMosqueListAdapter( var selectedFavoriteMosque: (FavoriteMosque)->Unit):RecyclerView.Adapter<FavoriteMosqueListAdapter.FavoriteMosqueViewHolder>() {

        val favoriteMosqueList= arrayListOf<FavoriteMosque>()

    class FavoriteMosqueViewHolder(val binding: RowFavoritemosqueBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMosqueViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoritemosqueBinding>(inflater, R.layout.row_favoritemosque, parent,false)
        return FavoriteMosqueListAdapter.FavoriteMosqueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMosqueViewHolder, position: Int) {

        val favoriteMosque= favoriteMosqueList[position]
        holder.binding.favoriteMosqueList=favoriteMosque

        AppUtil.pictureFavoriteMosque(favoriteMosque,holder.itemView.context,holder.binding.favoriteMosqueImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoriteMosque.invoke(favoriteMosque)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
            Navigation.findNavController(it).popBackStack()
            Navigation.findNavController(it).navigate(R.id.mosqueMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
            deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }
    }

    override fun getItemCount(): Int {
        return favoriteMosqueList.size
    }

    fun updateFavoriteMosqueList(newMosqueList: List<FavoriteMosque>) {

        favoriteMosqueList.clear()
        favoriteMosqueList.addAll(newMosqueList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context, position:Int, view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteMosqueDatabase.getInstance(context)?.favoriteMosqueDao()?.delete(favoriteMosqueList[position])

                favoriteMosqueList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, favoriteMosqueList.size)
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