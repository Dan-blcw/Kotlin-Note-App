package com.dan.noteapp.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dan.noteapp.R
import com.dan.noteapp.models.const
import com.dan.noteapp.models.dto.Note
import kotlin.random.Random

class NotesAdapter(private val context: Context,val ltn: NotesOnClickListener):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val noteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.apply {
            val colorLayout = findViewById<CardView>(R.id.cart_layout)
            val image = findViewById<ImageView>(R.id.imgNote)
            val title = findViewById<TextView>(R.id.txtTitle)
            val note = findViewById<TextView>(R.id.txtNote)
            val date = findViewById<TextView>(R.id.txtDate)
            val btn_delete = findViewById<ImageButton>(R.id.btn_delete)
            val index= random()
            const.create()

            title.text = noteList[position].title
            title.isSelected = true
            note.text = noteList[position].note
            date.text = noteList[position].date
            date.isSelected = true

            colorLayout.setCardBackgroundColor(resources.getColor(index,null))
            colorLayout.setOnClickListener {
                ltn.onItemClick(noteList[holder.adapterPosition])
            }
            btn_delete.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                dialog.apply {
                    setTitle("Confirm Delete Note")
                    setMessage("Have you confirmed to remove the item from the cart?")
                    setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                    setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
                        ltn.onDeleteItemClick(noteList[holder.adapterPosition])
                    }
                }.show()
            }
            Glide.with(context).load(const.listImg[position]).into(image)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        noteList.clear()
        noteList.addAll(newList)
        notifyDataSetChanged()
    }
    fun searchFilter(content: String){
        noteList.clear()
        for(nowIT in fullList){
            if(nowIT.title?.lowercase()?.contains(content.lowercase()) == true ||
                nowIT.note?.lowercase()?.contains(content.lowercase()) == true)
                noteList.add(nowIT)
        }
    }
    fun random(): Int{
        val list = ArrayList<Int>()
        list.add(R.color.do_1)
        list.add(R.color.do_2)
        list.add(R.color.do_3)
        list.add(R.color.do_4)
        list.add(R.color.do_5)
        list.add(R.color.do_6)
        list.add(R.color.do_7)


        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }
    interface NotesOnClickListener{
        fun onItemClick(note: Note)
        fun onDeleteItemClick(note: Note)
    }
}