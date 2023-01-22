package me.ako.logos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.ako.logos.databinding.ItemLogoBinding
import me.ako.logos.model.Logo

class LogoAdapter(private val onItemClicked: (Logo) -> Unit) :
    ListAdapter<Logo, LogoAdapter.LogoViewHolder>(DiffCallback()) {
    class DiffCallback : DiffUtil.ItemCallback<Logo>() {
        override fun areItemsTheSame(oldItem: Logo, newItem: Logo): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Logo, newItem: Logo): Boolean {
            return oldItem == newItem
        }
    }

    class LogoViewHolder(private val binding: ItemLogoBinding) : ViewHolder(binding.root) {
        fun onBind(item: Logo) {
            binding.logo = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLogoBinding.inflate(inflater, parent, false)
        return LogoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogoViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
    }
}