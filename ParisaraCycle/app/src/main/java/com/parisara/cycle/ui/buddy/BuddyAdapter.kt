package com.parisara.cycle.ui.buddy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.parisara.cycle.databinding.ItemBuddyBinding
import com.parisara.cycle.model.BuddyUser
import java.util.concurrent.TimeUnit

class BuddyAdapter : ListAdapter<BuddyUser, BuddyAdapter.BuddyViewHolder>(DiffCallback()) {

    inner class BuddyViewHolder(val binding: ItemBuddyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuddyViewHolder {
        val binding = ItemBuddyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuddyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuddyViewHolder, position: Int) {
        val buddy = getItem(position)
        holder.binding.apply {
            tvBuddyName.text = buddy.name
            val minAgo = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - buddy.lastSeen)
            tvLastSeen.text = if (minAgo < 1) "Active now" else "$minAgo min ago"
            tvStatus.text = if (buddy.isOnline) "🟢 Online" else "⚫ Offline"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BuddyUser>() {
        override fun areItemsTheSame(a: BuddyUser, b: BuddyUser) = a.uid == b.uid
        override fun areContentsTheSame(a: BuddyUser, b: BuddyUser) = a == b
    }
}
