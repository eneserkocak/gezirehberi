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
import com.eneserkocak.gezirehberi.databinding.RowFavoritebathBinding
import com.eneserkocak.gezirehberi.databinding.RowFavoritecaravanseraiBinding
import com.eneserkocak.gezirehberi.model.FavoriteBath
import com.eneserkocak.gezirehberi.model.FavoriteCaravanserai
import com.eneserkocak.gezirehberi.room.FavoriteBathDatabase
import com.eneserkocak.gezirehberi.room.FavoriteCaravanseraiDatabase

class FavoriteBathListAdapter( var selectedFavoriteBath: (FavoriteBath)->Unit):
    RecyclerView.Adapter<FavoriteBathListAdapter.FavoriteBathViewHolder>() {

    val favoriteBathList= arrayListOf<FavoriteBath>()

    class FavoriteBathViewHolder(val binding: RowFavoritebathBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBathListAdapter.FavoriteBathViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoritebathBinding>(inflater, R.layout.row_favoritebath, parent,false)
        return FavoriteBathListAdapter.FavoriteBathViewHolder(binding)
    }



    override fun onBindViewHolder(holder: FavoriteBathListAdapter.FavoriteBathViewHolder, position: Int) {

        val favoriteBath= favoriteBathList[position]
        holder.binding.favoriteBathList=favoriteBath

        AppUtil.pictureFavoriteBath(favoriteBath,holder.itemView.context,holder.binding.favoriteBathImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoriteBath.invoke(favoriteBath)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
            Navigation.findNavController(it).popBackStack()
            Navigation.findNavController(it).navigate(R.id.bathMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
            deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }
    }

    override fun getItemCount(): Int {
        return favoriteBathList.size
    }

    fun updateFavoriteBathList(newBathList: List<FavoriteBath>) {

        favoriteBathList.clear()
        favoriteBathList.addAll(newBathList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context, position:Int, view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteBathDatabase.getInstance(context)?.favoriteBathDao()?.delete(favoriteBathList[position])

                favoriteBathList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, favoriteBathList.size)
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