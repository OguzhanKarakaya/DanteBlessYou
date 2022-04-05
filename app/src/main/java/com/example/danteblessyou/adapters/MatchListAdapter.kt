package com.example.danteblessyou.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.danteblessyou.databinding.ItemMatchListBinding
import com.example.danteblessyou.model.MatchListModel

class MatchListAdapter(private val matchList: ArrayList<MatchListModel>) :
    RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMatchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    class ViewHolder(private val binding: ItemMatchListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MatchListModel) {
            binding.model = model
        }
    }
}