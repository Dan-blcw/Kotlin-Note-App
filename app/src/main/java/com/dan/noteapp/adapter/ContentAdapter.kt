package com.dan.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dan.noteapp.R


class ContentAdapter(private  val list: List<String>,val rvUtil: util): RecyclerView.Adapter<ContentAdapter.itemViewHolder>(){
    class itemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content,parent,false)
        return itemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.itemView.apply {
            val content = findViewById<TextView>(R.id.txtContent)
            content.text = list[position]

            holder.itemView.setOnClickListener {
                rvUtil.OnClickTitle(position)
            }
        }
    }
}