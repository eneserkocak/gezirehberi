package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowAncientlistBinding
import com.eneserkocak.gezirehberi.databinding.RowChurchlistBinding
import com.eneserkocak.gezirehberi.model.Ancientcity
import com.eneserkocak.gezirehberi.model.Church


class ChurchAdapter(val selectedChurch:(Church)->Unit ): RecyclerView.Adapter<ChurchAdapter.ChurchViewHolder>() {

    val churchList= arrayListOf<Church>()

    class ChurchViewHolder(val binding: RowChurchlistBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChurchAdapter.ChurchViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowChurchlistBinding>(inflater, R.layout.row_churchlist, parent,false)
        return ChurchAdapter.ChurchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChurchAdapter.ChurchViewHolder, position: Int) {
        val church = churchList[position]
        holder.binding.church=church

        AppUtil.pictureChurch(church,holder.itemView.context,holder.binding.churchListImage)

        holder.itemView.setOnClickListener {
            selectedChurch.invoke(church)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return churchList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateChurchList(newChurchList: List<Church>) {

        churchList.clear()
        churchList.addAll(newChurchList)
        notifyDataSetChanged()

    }



}