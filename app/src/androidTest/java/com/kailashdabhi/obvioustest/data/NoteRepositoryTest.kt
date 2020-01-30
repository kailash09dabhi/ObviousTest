package com.kailashdabhi.obvioustest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kailashdabhi.obvioustest.ServiceLocator
import com.kailashdabhi.obvioustest.base.Status
import com.kailashdabhi.obvioustest.data.database.Note
import com.kailashdabhi.obvioustest.data.database.NoteDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
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
class NoteRepositoryTest {
  lateinit var noteDatabase: NoteDatabase
  @get:Rule
  var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var coroutineTestRule = CoroutineTestRule()

  companion object {
    val NOTE_1 = Note(title = "Note 1", content = "Content 1")
  }

  private val notes = NoteRepository.notes()
  @Before
  fun setUp() {
    ServiceLocator.coroutineContext(Dispatchers.Main.immediate)
    noteDatabase = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      NoteDatabase::class.java
    ).allowMainThreadQueries().build()
    ServiceLocator.database(noteDatabase)
    notes.observeForever {}
  }

  @After
  fun tearDown() {
    noteDatabase.close()
  }

  @Test
  fun total_note_count_must_match_count_of_inserted_note() {
    ServiceLocator.database().noteDao().removeAllNotes()
    assertEquals(0, notes.value?.data?.size ?: 0)
    NoteRepository.insert(NOTE_1).observeForever {}
    assertEquals(1, notes.value?.data?.size)
  }

  @Test
  fun insert_note_function_should_save_title_and_content_of_note_as_it_is() {
    noteDatabase.noteDao().removeAllNotes()
    assertEquals(0, notes.value?.data?.size ?: 0)
    NoteRepository.insert(NOTE_1).observeForever {}
    assertEquals(1, notes.value?.data?.size ?: 0)
    assertEquals(NOTE_1.title, notes.value?.data?.get(0)?.title)
    assertEquals(NOTE_1.content, notes.value?.data?.get(0)?.content)
  }

  @Test
  fun insert_note_function_should_give_error_when_title_of_note_is_empty() {
    noteDatabase.noteDao().removeAllNotes()
    assertEquals(0, notes.value?.data?.size ?: 0)
    val insertLiveData = NoteRepository.insert(Note(title = "", content = NOTE_1.content))
    insertLiveData.observeForever {}
    assertEquals(Status.ERROR, insertLiveData.value?.status)
  }

  @Test
  fun insert_note_function_should_give_error_when_content_of_note_is_empty() {
    noteDatabase.noteDao().removeAllNotes()
    assertEquals(0, notes.value?.data?.size ?: 0)
    val insertLiveData = NoteRepository.insert(Note(title = NOTE_1.title, content = ""))
    insertLiveData.observeForever {}
    assertEquals(Status.ERROR, insertLiveData.value?.status)
  }
}