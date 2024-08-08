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
import com.eneserkocak.gezirehberi.databinding.RowFavoriteancientcityBinding
import com.eneserkocak.gezirehberi.databinding.RowFavoritecaravanseraiBinding
import com.eneserkocak.gezirehberi.model.FavoriteAncientcity
import com.eneserkocak.gezirehberi.model.FavoriteCaravanserai
import com.eneserkocak.gezirehberi.room.FavoriteAncientcityDatabase
import com.eneserkocak.gezirehberi.room.FavoriteCaravanseraiDatabase


class FavoriteCaravanseraiListAdapter( var selectedFavoriteCaravanserai: (FavoriteCaravanserai)->Unit):
    RecyclerView.Adapter<FavoriteCaravanseraiListAdapter.FavoriteCaravanseraiViewHolder>() {

    val favoriteCaravanseraiList= arrayListOf<FavoriteCaravanserai>()

    class FavoriteCaravanseraiViewHolder(val binding: RowFavoritecaravanseraiBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCaravanseraiListAdapter.FavoriteCaravanseraiViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoritecaravanseraiBinding>(inflater, R.layout.row_favoritecaravanserai, parent,false)
        return FavoriteCaravanseraiListAdapter.FavoriteCaravanseraiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCaravanseraiListAdapter.FavoriteCaravanseraiViewHolder, position: Int) {

        val favoriteCaravanserai= favoriteCaravanseraiList[position]
        holder.binding.favoriteCaravanseraiList=favoriteCaravanserai

        AppUtil.pictureFavoriteCaravanserai(favoriteCaravanserai,holder.itemView.context,holder.binding.favoriteCaravanseraiImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoriteCaravanserai.invoke(favoriteCaravanserai)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
            Navigation.findNavController(it).popBackStack()
            Navigation.findNavController(it).navigate(R.id.caravanseraiMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
            deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }
    }

    override fun getItemCount(): Int {
        return favoriteCaravanseraiList.size
    }

    fun updateFavoriteCaravanseraiList(newCaravanseraiList: List<FavoriteCaravanserai>) {

        favoriteCaravanseraiList.clear()
        favoriteCaravanseraiList.addAll(newCaravanseraiList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context, position:Int, view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteCaravanseraiDatabase.getInstance(context)?.favoriteCaravanseraiDao()?.delete(favoriteCaravanseraiList[position])

                favoriteCaravanseraiList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, favoriteCaravanseraiList.size)
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