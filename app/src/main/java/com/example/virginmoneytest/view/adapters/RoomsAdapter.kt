package com.example.virginmoneytest.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.virginmoneytest.R
import com.example.virginmoneytest.databinding.ItemRoomsBinding
import com.example.virginmoneytest.model.Room

class RoomsAdapter : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {

    inner class RoomsViewHolder(
        private val binding: ItemRoomsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            binding.room = room

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoomsAdapter.RoomsViewHolder {
        return RoomsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_rooms,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Room>) = differ.submitList(list)
}
