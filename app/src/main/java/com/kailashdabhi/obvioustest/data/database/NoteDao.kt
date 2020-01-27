package com.kailashdabhi.obvioustest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertNote(note: Note): Long

  @Query("SELECT * FROM Note")
  fun noteList(): LiveData<List<Note>>
}