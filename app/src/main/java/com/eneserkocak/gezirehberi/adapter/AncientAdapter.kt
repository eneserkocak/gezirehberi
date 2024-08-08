package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowAncientlistBinding

import com.eneserkocak.gezirehberi.model.Ancientcity


class AncientAdapter(val selectedAncient:(Ancientcity)->Unit ): RecyclerView.Adapter<AncientAdapter.AncientViewHolder>() {

    val ancientList= arrayListOf<Ancientcity>()

    class AncientViewHolder(val binding: RowAncientlistBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AncientAdapter.AncientViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowAncientlistBinding>(inflater, R.layout.row_ancientlist, parent,false)
        return AncientAdapter.AncientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AncientAdapter.AncientViewHolder, position: Int) {
        val ancient = ancientList[position]
        holder.binding.ancient=ancient

        AppUtil.pictureAncintcity(ancient,holder.itemView.context,holder.binding.ancientListImage)

        holder.itemView.setOnClickListener {
            selectedAncient.invoke(ancient)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return ancientList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateAncientList(newAncientList: List<Ancientcity>) {

        ancientList.clear()
        ancientList.addAll(newAncientList)
        notifyDataSetChanged()

    }



}