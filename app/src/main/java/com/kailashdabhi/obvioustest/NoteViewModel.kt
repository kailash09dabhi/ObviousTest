package com.kailashdabhi.obvioustest

import androidx.lifecycle.ViewModel
import com.kailashdabhi.obvioustest.data.NoteRepository
import com.kailashdabhi.obvioustest.data.database.Note

/**
 * @author kailash09dabhi@gmail.com
 * @date 22, Jan 2020 (16:29)
 */
class NoteViewModel : ViewModel() {
  fun notes() = NoteRepository.notes()
  fun insertNote(note: Note) = NoteRepository.insert(note)
}