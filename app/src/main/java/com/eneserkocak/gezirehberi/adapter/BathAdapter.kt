package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowAncientlistBinding
import com.eneserkocak.gezirehberi.databinding.RowBathlistBinding
import com.eneserkocak.gezirehberi.model.Ancientcity
import com.eneserkocak.gezirehberi.model.Bath


class BathAdapter(val selectedBath:(Bath)->Unit ): RecyclerView.Adapter<BathAdapter.BathViewHolder>() {

    val bathList= arrayListOf<Bath>()

    class BathViewHolder(val binding: RowBathlistBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BathAdapter.BathViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowBathlistBinding>(inflater, R.layout.row_bathlist, parent,false)
        return BathAdapter.BathViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BathAdapter.BathViewHolder, position: Int) {
        val bath = bathList[position]
        holder.binding.bath=bath

        AppUtil.pictureBath(bath,holder.itemView.context,holder.binding.bathListImage)

        holder.itemView.setOnClickListener {
            selectedBath.invoke(bath)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return bathList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateBathList(newBathList: List<Bath>) {

        bathList.clear()
        bathList.addAll(newBathList)
        notifyDataSetChanged()

    }



}