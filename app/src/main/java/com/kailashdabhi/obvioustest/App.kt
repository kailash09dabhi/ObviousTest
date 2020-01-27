package com.kailashdabhi.obvioustest

import android.app.Application
import com.kailashdabhi.obvioustest.data.database.NoteDatabase

/**
 * @author kailash09dabhi@gmail.com
 * @date 23, Jan 2020 (12:35)
 */
class App : Application() {
  override fun onCreate() {
    super.onCreate()
    ServiceLocator.database(NoteDatabase.from(this))
  }
}