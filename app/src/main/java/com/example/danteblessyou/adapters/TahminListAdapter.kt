package com.example.danteblessyou.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.danteblessyou.databinding.ActivityTahminBinding
import com.example.danteblessyou.databinding.ItemTahminBinding
import com.example.danteblessyou.model.FavoriteModel

class TahminListAdapter(private val favoriteList: ArrayList<FavoriteModel>): RecyclerView.Adapter<TahminListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TahminListAdapter.ViewHolder {
        val binding = ItemTahminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TahminListAdapter.ViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    class ViewHolder(private val binding: ItemTahminBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: FavoriteModel) {
            binding.model = model
        }
    }
}