package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowLakelistBinding
import com.eneserkocak.gezirehberi.databinding.RowMuseumlistBinding
import com.eneserkocak.gezirehberi.model.Lake
import com.eneserkocak.gezirehberi.model.Museum

class MuseumAdapter(val selectedMuseum: (Museum)->Unit): RecyclerView.Adapter<MuseumAdapter.MuseumViewHolder>() {

    val museumList= arrayListOf<Museum>()
    class MuseumViewHolder(val binding:RowMuseumlistBinding):RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumAdapter.MuseumViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowMuseumlistBinding>(inflater, R.layout.row_museumlist, parent,false)
        return MuseumAdapter.MuseumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MuseumAdapter.MuseumViewHolder, position: Int) {

        val museum = museumList[position]
        holder.binding.museum=museum

        AppUtil.pictureMuseum(museum,holder.itemView.context,holder.binding.museumListImage)

        holder.itemView.setOnClickListener {
            selectedMuseum.invoke(museum)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return museumList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateMuseumList(newMuseumList: List<Museum>) {

        museumList.clear()
        museumList.addAll(newMuseumList)
        notifyDataSetChanged()

    }
}