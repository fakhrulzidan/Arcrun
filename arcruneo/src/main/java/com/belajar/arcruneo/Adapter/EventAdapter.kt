package com.belajar.arcruneo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajar.arcruneo.Model.EventModel
import com.belajar.arcruneo.R
import com.belajar.arcruneo.databinding.ViewholderEventBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class EventAdapter(
    private val items: ArrayList<EventModel>, // Pastikan tipe ini benar
    private val context: Context
) : RecyclerView.Adapter<EventAdapter.Viewholder>() {

    inner class Viewholder(private val binding: ViewholderEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventModel) {
            binding.namaEventTxt.text = item.nama_event
            binding.statusEventTxt.text = item.status_event
            binding.hargaTxt.text = "Rp.${item.harga}"
            binding.batasAkhirTxt.text = item.batas_akhir

            // RequestOptions: Tambahkan konfigurasi untuk gambar
            val requestOptions = RequestOptions()
                .error(R.drawable.imagenotfound)
//                .transform(CenterCrop(), RoundedCorners(30))

            // Load gambar dengan Glide
            Glide.with(context)
                .load(item.gambar)
                .apply (requestOptions)
                .into(binding.gambarBg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}