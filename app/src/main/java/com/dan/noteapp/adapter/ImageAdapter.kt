package com.dan.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dan.noteapp.R
import com.dan.noteapp.models.const


class ImageAdapter(private  val list: List<String>): RecyclerView.Adapter<ImageAdapter.itemViewHolder>(){
    class itemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_imagead,parent,false)
        return itemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.itemView.apply {
            val imageF = findViewById<ImageView>(R.id.imgRvStaggeredGrid)
            Glide.with(context).load(const.listImg[position]).into(imageF)

        }
    }
}