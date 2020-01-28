package com.kailashdabhi.obvioustest

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.kailashdabhi.obvioustest.base.BaseFragment
import com.kailashdabhi.obvioustest.data.database.Note
import kotlinx.android.synthetic.main.fragment_note_detail.date
import kotlinx.android.synthetic.main.item_note.content
import kotlinx.android.synthetic.main.item_note.title

/**
 * @author kailash09dabhi@gmail.com
 * @date 27, Jan 2020 (11:18)
 */
class NoteDetailFragment : BaseFragment() {
  companion object {
    private const val ARGS_NOTE = "note"

    fun newInstance(note: Note): NoteDetailFragment {
      val noteDetailFragment = NoteDetailFragment()
      noteDetailFragment.arguments = Bundle().apply {
        putParcelable(ARGS_NOTE, note)
      }
      return noteDetailFragment
    }
  }

  override fun layoutId() = R.layout.fragment_note_detail

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
    activity?.title = "Note Detail"
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    arguments?.getParcelable<Note>(ARGS_NOTE)?.let {
      title.text = "Title: " + it.title
      content.text = "Content: " + it.content
      date.text = "Date: " + App.dateFormat.format(it.createdAt)
    }
    view.isFocusableInTouchMode = true
    view.requestFocus()
    view.setOnKeyListener { _, keyCode, _ ->
      if (keyCode == KeyEvent.KEYCODE_BACK) {
        parentFragmentManager.popBackStack(
          CreateNoteFragment.javaClass.name,
          FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
      }
      true
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return if (item.itemId == android.R.id.home) {
      parentFragmentManager.popBackStack(
        CreateNoteFragment.javaClass.name,
        FragmentManager.POP_BACK_STACK_INCLUSIVE
      )
      true
    } else
      super.onOptionsItemSelected(item)
  }
}