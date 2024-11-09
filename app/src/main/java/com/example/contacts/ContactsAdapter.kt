package com.example.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(private val context: Context, private val listener: ContactClickListener) :
    RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    private val contacts = ArrayList<Contacts>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Contacts>) {
        contacts.clear()
        contacts.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        private val phoneTV: TextView = itemView.findViewById(R.id.phoneTV)
        private val dateTV: TextView = itemView.findViewById(R.id.dateTV)
        private val addressTV: TextView = itemView.findViewById(R.id.addressTV)
        val deleteIV: ImageView = itemView.findViewById(R.id.deleteIV)

        fun bind(contacts: Contacts) {
            nameTV.text = contacts.name
            phoneTV.text = contacts.phone
            dateTV.text = contacts.date
            addressTV.text = contacts.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val viewHolder = ContactViewHolder(
            LayoutInflater.from(context).inflate((R.layout.list_item), parent, false)
        )
        viewHolder.deleteIV.setOnClickListener {
            listener.onItemClick(contacts[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contacts[position]
        holder.bind(currentContact)
    }

    interface ContactClickListener {
        fun onItemClick(contacts: Contacts)
    }
}