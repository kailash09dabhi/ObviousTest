package com.kailashdabhi.obvioustest

import com.kailashdabhi.obvioustest.data.database.NoteDatabase

/**
 * @author Kailash Dabhi
 * @email kailash09dabhi@gmail.com
 * @date 18/02/2019
 */
object ServiceLocator {
  private lateinit var database: NoteDatabase

  fun database(): NoteDatabase {
    return database
  }

  fun database(noteDatabase: NoteDatabase) {
    database = noteDatabase
  }
}