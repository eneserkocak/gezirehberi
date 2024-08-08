package com.eneserkocak.gezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eneserkocak.gezirehberi.R
import com.eneserkocak.gezirehberi.Util.AppUtil
import com.eneserkocak.gezirehberi.databinding.RowParklistBinding
import com.eneserkocak.gezirehberi.model.Park

class ParksAdapter(val selectedPark: (Park)->Unit): RecyclerView.Adapter<ParksAdapter.ParksViewHolder>() {

        val parkList= arrayListOf<Park>()

    class ParksViewHolder(val binding: RowParklistBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParksViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<RowParklistBinding>(inflater, R.layout.row_parklist, parent,false)
        return ParksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParksViewHolder, position: Int) {

        val park= parkList[position]
        holder.binding.park= park

        AppUtil.gorselIndir(park,holder.itemView.context,holder.binding.parkListImage)

        holder.itemView.setOnClickListener {
            selectedPark.invoke(park)
            AppUtil.longToast(holder.itemView.context,"Gideceğiniz adrese ait Marker'ı görmek için haritayı elinizle küçültün")

        }


    }

    override fun getItemCount(): Int {
        return parkList.size
    }

    //RECYCLER İÇİNDE GÖRSELLER KARIŞIYORDU..AŞAĞIDAKİ 2 FONKSİYONU KOYUNCA ..DÜZELDİ......
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }



    fun updateParkingList(newParkList: List<Park>) {

        parkList.clear()
        parkList.addAll(newParkList)
        notifyDataSetChanged()

    }


}