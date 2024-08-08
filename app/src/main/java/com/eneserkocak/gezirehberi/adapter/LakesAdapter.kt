package com.eneserkocak.gezirehberi.adapter

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.FragmentLakesListBinding

import com.eneserkocak.gezirehberi.databinding.RowLakelistBinding
import com.eneserkocak.gezirehberi.model.Lake
import com.eneserkocak.gezirehberi.model.Park

class LakesAdapter(val selectedLake: (Lake)->Unit):RecyclerView.Adapter<LakesAdapter.LakesViewHolder>() {

    val lakeList= arrayListOf<Lake>()

    class LakesViewHolder(val binding:RowLakelistBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LakesViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowLakelistBinding>(inflater, R.layout.row_lakelist, parent,false)
        return LakesAdapter.LakesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LakesViewHolder, position: Int) {

        val lake = lakeList[position]
        holder.binding.lake=lake

        AppUtil.gorselLake(lake,holder.itemView.context,holder.binding.lakeListImage)

        holder.itemView.setOnClickListener {
            selectedLake.invoke(lake)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return lakeList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateLakeList(newLakeList: List<Lake>) {

        lakeList.clear()
        lakeList.addAll(newLakeList)
        notifyDataSetChanged()

    }



}