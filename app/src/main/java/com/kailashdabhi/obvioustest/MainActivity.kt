package com.kailashdabhi.obvioustest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(
          R.id.fragmentContainer,
          NoteListFragment.newInstance()
        )
        .addToBackStack(NoteListFragment.javaClass.name)
        .commit()
    }
  }

  override fun onBackPressed() {
    if (supportFragmentManager.backStackEntryCount == 1) {
      finish()
    } else {
      super.onBackPressed()
    }
  }
}
