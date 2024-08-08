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
import com.eneserkocak.gezirehberi.databinding.RowFavoritecaravanseraiBinding
import com.eneserkocak.gezirehberi.databinding.RowFavoritechurchBinding
import com.eneserkocak.gezirehberi.model.FavoriteCaravanserai
import com.eneserkocak.gezirehberi.model.FavoriteChurch
import com.eneserkocak.gezirehberi.room.FavoriteCaravanseraiDatabase
import com.eneserkocak.gezirehberi.room.FavoriteChurchDatabase

class FavoriteChurchListAdapter( var selectedFavoriteChurch: (FavoriteChurch)->Unit):
    RecyclerView.Adapter<FavoriteChurchListAdapter.FavoriteChurchViewHolder>() {

    val favoriteChurchList= arrayListOf<FavoriteChurch>()

    class FavoriteChurchViewHolder(val binding: RowFavoritechurchBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteChurchListAdapter.FavoriteChurchViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoritechurchBinding>(inflater, R.layout.row_favoritechurch, parent,false)
        return FavoriteChurchListAdapter.FavoriteChurchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteChurchListAdapter.FavoriteChurchViewHolder, position: Int) {

        val favoriteChurch= favoriteChurchList[position]
        holder.binding.favoriteChurchList=favoriteChurch

        AppUtil.pictureFavoriteChurch(favoriteChurch,holder.itemView.context,holder.binding.favoriteChurchImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoriteChurch.invoke(favoriteChurch)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
            Navigation.findNavController(it).popBackStack()
            Navigation.findNavController(it).navigate(R.id.churchMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
            deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }
    }

    override fun getItemCount(): Int {
        return favoriteChurchList.size
    }

    fun updateFavoriteChurchList(newChurchList: List<FavoriteChurch>) {

        favoriteChurchList.clear()
        favoriteChurchList.addAll(newChurchList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context, position:Int, view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteChurchDatabase.getInstance(context)?.favoriteChurchDao()?.delete(favoriteChurchList[position])

                favoriteChurchList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, favoriteChurchList.size)
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