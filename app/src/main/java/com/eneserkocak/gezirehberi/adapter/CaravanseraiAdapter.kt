package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowCaravanserailistBinding

import com.eneserkocak.gezirehberi.model.Caravanserai


class CaravanseraiAdapter(val selectedCaravanserai:(Caravanserai)->Unit ): RecyclerView.Adapter<CaravanseraiAdapter.CaravanseraiViewHolder>() {

    val caravanseraiList= arrayListOf<Caravanserai>()

    class CaravanseraiViewHolder(val binding: RowCaravanserailistBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaravanseraiAdapter.CaravanseraiViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowCaravanserailistBinding>(inflater, R.layout.row_caravanserailist, parent,false)
        return CaravanseraiAdapter.CaravanseraiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CaravanseraiAdapter.CaravanseraiViewHolder, position: Int) {
        val caravanserai = caravanseraiList[position]
        holder.binding.caravanserai=caravanserai

        AppUtil.pictureCaravanserai(caravanserai,holder.itemView.context,holder.binding.caravanseraiListImage)

        holder.itemView.setOnClickListener {
            selectedCaravanserai.invoke(caravanserai)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return caravanseraiList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateCaravanseraiList(newCaravanseraiList: List<Caravanserai>) {

        caravanseraiList.clear()
        caravanseraiList.addAll(newCaravanseraiList)
        notifyDataSetChanged()

    }



}