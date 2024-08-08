package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowMosquelistBinding
import com.eneserkocak.gezirehberi.databinding.RowMuseumlistBinding
import com.eneserkocak.gezirehberi.model.Mosque
import com.eneserkocak.gezirehberi.model.Museum

class MosqueAdapter(val selectedMosque:(Mosque)->Unit ):RecyclerView.Adapter<MosqueAdapter.MosqueViewHolder>() {

        val mosqueList= arrayListOf<Mosque>()

    class MosqueViewHolder(val binding:RowMosquelistBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MosqueViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowMosquelistBinding>(inflater, R.layout.row_mosquelist, parent,false)
        return MosqueAdapter.MosqueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MosqueViewHolder, position: Int) {
        val mosque = mosqueList[position]
        holder.binding.mosque=mosque

        AppUtil.pictureMosque(mosque,holder.itemView.context,holder.binding.mosqueListImage)

        holder.itemView.setOnClickListener {
            selectedMosque.invoke(mosque)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")
        }
    }

    override fun getItemCount(): Int {
        return mosqueList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateMosqueList(newMosqueList: List<Mosque>) {

        mosqueList.clear()
        mosqueList.addAll(newMosqueList)
        notifyDataSetChanged()

    }
}