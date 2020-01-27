package com.kailashdabhi.obvioustest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [Note::class],
  exportSchema = false,
  version = 1
)
abstract class NoteDatabase : RoomDatabase() {

  companion object {
    private lateinit var instance: NoteDatabase
    fun from(context: Context): NoteDatabase {
      if (!::instance.isInitialized) {
        instance = Room.databaseBuilder(
          context.applicationContext,
          NoteDatabase::class.java,
          "note_database"
        )
          .fallbackToDestructiveMigration()
          .build()
      }
      return instance
    }
  }

  abstract fun noteDao(): NoteDao
}