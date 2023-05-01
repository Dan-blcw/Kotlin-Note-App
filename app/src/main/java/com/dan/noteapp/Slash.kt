package com.dan.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dan.noteapp.databinding.ActivityMainBinding
import com.dan.noteapp.databinding.ActivitySlashBinding

class Slash : AppCompatActivity() {
    private lateinit var binding: ActivitySlashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this@Slash,Main::class.java)
            startActivity(intent)
            finish()
        }
    }

}