package com.example.meuscontatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.list

//Essa classe gerencia toda lista
class ContactAdapter(var listener: ClickItemContactListener) : RecyclerView.Adapter<ContactAdapter.ContactAapterViewHolder>() {

    private val list: MutableList<Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAapterViewHolder(view, list, listener)
    }

    override fun onBindViewHolder(holder: ContactAapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList (list: List<Contact>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
//Essa classe gerencia cada item da lista
    class ContactAapterViewHolder(itemView: View, var list: List<Contact>, var listener: ClickItemContactListener) : RecyclerView.ViewHolder(itemView){

        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
        private val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)

        init {
            itemView.setOnClickListener {
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: Contact){
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }
    }
}