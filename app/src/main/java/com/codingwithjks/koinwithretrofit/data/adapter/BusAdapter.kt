package com.codingwithjks.koinwithretrofit.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.koinwithretrofit.data.Bus
import com.codingwithjks.koinwithretrofit.databinding.EachRowBinding

class BusAdapter : ListAdapter<Bus,BusAdapter.BusViewHolder>(Diff) {

    class BusViewHolder(private val binding:EachRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(bus:Bus){
            binding.apply {
                busNo.text = bus.bus_no
                towns.text = bus.towns
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Bus>(){
        override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem.bus_no == newItem.bus_no
        }

        override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        return BusViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val bus = getItem(position)
        if(bus!=null){
            holder.bind(bus)
        }
    }
}