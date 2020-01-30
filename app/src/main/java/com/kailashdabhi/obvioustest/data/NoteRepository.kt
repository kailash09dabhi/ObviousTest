package com.kailashdabhi.obvioustest.data

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.kailashdabhi.obvioustest.ServiceLocator
import com.kailashdabhi.obvioustest.base.Resource
import com.kailashdabhi.obvioustest.data.database.Note
import kotlinx.coroutines.Dispatchers

/**
 * @author kailash09dabhi@gmail.com
 * @date 21, Jan 2020 (17:42)
 */
object NoteRepository {
  fun notes() = liveData<Resource<List<Note>>>(Dispatchers.IO) {
    emitSource(
      ServiceLocator.database().noteDao().noteList()
        .map {
          Resource.success(it)
        }
    )
  }

  fun insert(note: Note) = liveData(ServiceLocator.coroutineContext()) {
    if (note.title.isNullOrEmpty() || note.content.isNullOrEmpty()) {
      emit(
        Resource.error(
          "Note's title or content must not be empty.",
          note
        )
      )
      return@liveData
    }
    val noteId = ServiceLocator.database().noteDao().insertNote(note)
    if (noteId >= 0)
      emit(Resource.success(note.copy(id = noteId)))
    else emit(
      Resource.error(
        "Note insert operation failed.",
        note
      )
    )
  }
}