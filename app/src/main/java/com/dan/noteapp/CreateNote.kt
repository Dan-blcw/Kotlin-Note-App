package com.dan.noteapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dan.noteapp.databinding.ActivityCreateNoteBinding
import com.dan.noteapp.models.dto.Note
import java.text.SimpleDateFormat
import java.util.*
@Suppress("DEPRECATION")
class CreateNote : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding
    private lateinit var note: Note
    private lateinit var old_note: Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.edtTitle.setText(old_note.title)
            binding.edtNote.setText(old_note.note)
            isUpdate = true
        }catch (exception: Exception){
            exception.printStackTrace()
        }
        binding.btnDone.setOnClickListener {
            val title_str = binding.edtTitle.text.toString()
            val note_str = binding.edtNote.text.toString()
            if(title_str.isNotEmpty() || note_str.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
                if(isUpdate){
                    note = Note(old_note.id,title_str,note_str,formatter.format(Date()))
                }else{
                    note = Note(null,title_str,note_str,formatter.format(Date()))
                }
                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }else{
                Toast.makeText(this@CreateNote, "Please Typing some data !!!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }
        binding.btnExit.setOnClickListener {
            onBackPressed()
        }
        binding.btnFavorite.setOnClickListener {
            Toast.makeText(this@CreateNote,"Added to success list",Toast.LENGTH_LONG).show()
        }
    }
}