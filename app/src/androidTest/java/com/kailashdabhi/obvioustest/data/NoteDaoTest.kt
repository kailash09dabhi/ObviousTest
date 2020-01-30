package com.kailashdabhi.obvioustest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kailashdabhi.obvioustest.ServiceLocator
import com.kailashdabhi.obvioustest.data.database.Note
import com.kailashdabhi.obvioustest.data.database.NoteDao
import com.kailashdabhi.obvioustest.data.database.NoteDatabase
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author kailash09dabhi@gmail.com
 * @date 29, Jan 2020 (18:35)
 */
@RunWith(AndroidJUnit4::class)
class NoteDaoTest {
  lateinit var dao: NoteDao
  lateinit var noteDatabase: NoteDatabase
  @get:Rule
  var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  companion object {
    val NOTE_1 = Note(title = "Note 1", content = "Content 1")
    val NOTE_2 = Note(title = "Note 2", content = "Content 2")
    val NOTE_3 = Note(title = "Note 3", content = "Content 3")
  }

  @Before
  fun setUp() {
    noteDatabase = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      NoteDatabase::class.java
    ).build()
    ServiceLocator.database(noteDatabase)
    dao = noteDatabase.noteDao()
  }

  @After
  fun tearDown() {
    noteDatabase.close()
  }

  @Test
  fun total_note_count_must_match_count_of_inserted_note() {
    dao.insertNote(NOTE_1)
    dao.insertNote(NOTE_2)
    dao.insertNote(NOTE_3)
    val noteList = dao.noteList()
    noteList.observeForever { }
    assertEquals(3, noteList.value?.size)
  }

  @Test
  fun insert_note_function_should_save_title_and_content_as_it_is() {
    dao.insertNote(NOTE_1)
    val noteList = dao.noteList()
    noteList.observeForever { }
    assertEquals(NOTE_1.title, noteList.value?.get(0)?.title)
    assertEquals(NOTE_1.content, noteList.value?.get(0)?.content)
  }
}