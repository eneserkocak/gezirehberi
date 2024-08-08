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
import com.eneserkocak.gezirehberi.databinding.RowFavoritelakeBinding
import com.eneserkocak.gezirehberi.databinding.RowHistorylistBinding
import com.eneserkocak.gezirehberi.model.FavoriteHistory
import com.eneserkocak.gezirehberi.model.FavoriteLake
import com.eneserkocak.gezirehberi.model.History
import com.eneserkocak.gezirehberi.room.FavoriteHistoryDatabase
import com.eneserkocak.gezirehberi.room.FavoriteLakesDatabase

class FavoriteHistoryListAdapter(val selectedFavoriteHistory: (FavoriteHistory)->Unit):RecyclerView.Adapter<FavoriteHistoryListAdapter.FavoriteHistoryListViewHolder>() {

            val favoriteHistoryPlaceList= arrayListOf<FavoriteHistory>()

    class FavoriteHistoryListViewHolder(val binding: RowFavoritehistoryBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteHistoryListViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowFavoritehistoryBinding>(inflater, R.layout.row_favoritehistory, parent,false)
        return FavoriteHistoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHistoryListViewHolder, position: Int) {
        val favoriteHistory= favoriteHistoryPlaceList[position]
        holder.binding.favoriteHistoryList= favoriteHistory

        AppUtil.pictureFavoriteHistory(favoriteHistory,holder.itemView.context,holder.binding.favoriteHistoryImage)

        holder.binding.navigateBtn.setOnClickListener {
            selectedFavoriteHistory.invoke(favoriteHistory)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
            Navigation.findNavController(it).popBackStack()
            Navigation.findNavController(it).navigate(R.id.historyMapsFragment)
        }
        holder.binding.deleteBtn.setOnClickListener {
            deletePlaceDialog(holder.itemView.context,position,holder.binding.root)
        }


    }

    override fun getItemCount(): Int {
        return favoriteHistoryPlaceList.size
    }

    fun updateFavoriteHistoryList(newHistoryList: List<FavoriteHistory>) {

        favoriteHistoryPlaceList.clear()
        favoriteHistoryPlaceList.addAll(newHistoryList)
        notifyDataSetChanged()

    }

    fun deletePlaceDialog(context: Context, position:Int, view: View){

        val alert = AlertDialog.Builder(context)
        alert.setMessage("Favori Listemden Sil")

        alert.setPositiveButton("EVET", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                FavoriteHistoryDatabase.getInstance(context)?.favoriteHistoryDao()?.delete(favoriteHistoryPlaceList[position])

                favoriteHistoryPlaceList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, favoriteHistoryPlaceList.size)
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