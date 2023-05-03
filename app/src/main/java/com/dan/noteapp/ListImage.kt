package com.dan.noteapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dan.noteapp.adapter.ImageAdapter
import com.dan.noteapp.databinding.ActivityListImageBinding
import com.dan.noteapp.models.const

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityListImageBinding
class ListImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        binding.btnCb.setOnClickListener {
            onBackPressed()
        }

//        const.create()
//        binding.RvStaggeredGrid.layoutManager = StaggeredGridLayoutManager(2,
//            StaggeredGridLayoutManager.VERTICAL)
//        val itemAdapter = ImageAdapter(const.listImg)
//        binding.RvStaggeredGrid.adapter = itemAdapter
    }
}