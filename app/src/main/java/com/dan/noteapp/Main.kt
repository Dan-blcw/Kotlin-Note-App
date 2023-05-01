package com.dan.noteapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dan.noteapp.adapter.ContentAdapter
import com.dan.noteapp.adapter.NotesAdapter
import com.dan.noteapp.adapter.util
import com.dan.noteapp.databinding.ActivityMainBinding
import com.dan.noteapp.dbase.NoteDB
import com.dan.noteapp.models.NotesViewModel
import com.dan.noteapp.models.dto.Note

@Suppress("DEPRECATION")
class Main : AppCompatActivity() ,NotesAdapter.NotesOnClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbase: NoteDB
    lateinit var viewModel: NotesViewModel
    lateinit var adapter: NotesAdapter
    lateinit var takeNote: Note
    private  val updateNote = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if(result.resultCode == Activity.RESULT_OK){
            val note = result.data?.getSerializableExtra("note") as? Note
            if(note != null){
                viewModel.updateNote(note)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Roll0()
        Roll1()
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this){list->
            list?.let{
                adapter.updateList(list)
            }
        }
        dbase = NoteDB.getDB(this)

    }

    private fun Roll0() {
        var list = mutableListOf<String>()
        list.add("All Notes")
        list.add("Important")
        list.add("To-Do")
        list.add("Nothing")
        list.add("Yohohoho")
        val rvAdapter = ContentAdapter(list,object: util{
            override fun OnClickTitle(pos: Int) {
                Toast.makeText(
                    this@Main,
                    "Click ${list[pos]}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        binding.rvContent.adapter = rvAdapter
        binding.rvContent.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun Roll1() {
        binding.rview.setHasFixedSize(true)
        binding.rview.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter = NotesAdapter(this,this)
        binding.rview.adapter = adapter
        val getContext = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

            val note = result.data?.getSerializableExtra("note") as? Note
            if(note != null){
                viewModel.insert(note)
            }
        }

        binding.btnCreateNote.setOnClickListener{
            val intent = Intent(this, CreateNote::class.java)
            getContext.launch(intent)
        }
        //search
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText !=null){
                    adapter.searchFilter(newText)
                }
                return true
            }
        })
    }

    override fun onItemClick(note: Note) {
        val intent = Intent(this@Main, CreateNote::class.java)
        intent.putExtra("current_note",note)
        updateNote.launch(intent)
    }

    override fun onDeleteItemClick(note: Note) {
        viewModel.deleteNote(note)
    }
}


