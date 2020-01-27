package com.kailashdabhi.obvioustest

import android.app.Application
import com.kailashdabhi.obvioustest.data.database.NoteDatabase
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author kailash09dabhi@gmail.com
 * @date 23, Jan 2020 (12:35)
 */
class App : Application() {
  override fun onCreate() {
    super.onCreate()
    ServiceLocator.database(NoteDatabase.from(this))
  }

  companion object {
    //format eg. 13 January 2018, 5:30 PM
    val dateFormat = SimpleDateFormat("dd MMMM yyyy, hh:mm a", Locale.getDefault())
  }
}