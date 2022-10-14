package com.example.virginmoneytest.view.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.virginmoneytest.R
import com.example.virginmoneytest.databinding.ItemContactBinding
import com.example.virginmoneytest.model.Contact


class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(
        private val binding: ItemContactBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.contact = contact
            if (contact.avatar != null && !contact.avatar.equals("", true)) {
                Glide.with(itemView.context).load(contact.avatar)
                    .placeholder(R.drawable.ic_round_person).circleCrop()
                    .into(binding.contactImage)
            }
            binding.email.setOnClickListener {
                openEmailIntent(it, contact)
            }
        }
    }

    private fun openEmailIntent(it: View, contact: Contact) {
        if (contact.email != null && !contact.email.equals("", true)) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(contact.email))
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            it.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        return ContactViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_contact,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Contact>) = differ.submitList(list)

}
