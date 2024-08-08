package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowHistorylistBinding
import com.eneserkocak.gezirehberi.databinding.RowLakelistBinding
import com.eneserkocak.gezirehberi.model.History
import com.eneserkocak.gezirehberi.model.Lake

class HistoryAdapter (val selectedHistory: (History)->Unit): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    val historyPlacesList= arrayListOf<History>()

    class HistoryViewHolder(val binding: RowHistorylistBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowHistorylistBinding>(inflater, R.layout.row_historylist, parent,false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        val history = historyPlacesList[position]
        holder.binding.history=history

        AppUtil.pictureHistory(history,holder.itemView.context,holder.binding.historyListImage)

        holder.itemView.setOnClickListener {
            selectedHistory.invoke(history)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return historyPlacesList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateHistoryList(newHistoryList: List<History>) {

        historyPlacesList.clear()
        historyPlacesList.addAll(newHistoryList)
        notifyDataSetChanged()

    }

}