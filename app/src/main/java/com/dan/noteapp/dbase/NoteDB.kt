package com.dan.noteapp.dbase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dan.noteapp.models.dto.Note

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDB: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    companion object{
        @Volatile
        private var INSTANCE: NoteDB? = null

        fun getDB(context: Context): NoteDB{
            return  INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDB::class.java,
                    "dbdemo"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}