package com.kailashdabhi.obvioustest

import com.kailashdabhi.obvioustest.data.database.NoteDatabase
import kotlin.coroutines.CoroutineContext

/**
 * @author Kailash Dabhi
 * @email kailash09dabhi@gmail.com
 * @date 18/02/2019
 */
object ServiceLocator {
  private lateinit var database: NoteDatabase
  private lateinit var coroutineContext: CoroutineContext

  fun database() = database

  fun database(noteDatabase: NoteDatabase) {
    database = noteDatabase
  }

  fun coroutineContext() = coroutineContext

  fun coroutineContext(coroutineContext: CoroutineContext) {
    this.coroutineContext = coroutineContext
  }
}