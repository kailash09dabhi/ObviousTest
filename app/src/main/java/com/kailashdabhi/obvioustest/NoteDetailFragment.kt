package com.kailashdabhi.obvioustest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kailashdabhi.obvioustest.data.database.Note
import kotlinx.android.synthetic.main.fragment_note_detail.date
import kotlinx.android.synthetic.main.item_note.content
import kotlinx.android.synthetic.main.item_note.title

/**
 * @author kailash09dabhi@gmail.com
 * @date 27, Jan 2020 (11:18)
 */
class NoteDetailFragment : Fragment() {
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

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_note_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(false)
    arguments?.getParcelable<Note>(ARGS_NOTE)?.let {
      title.text = it.title
      content.text = it.content
//      13 January 2018, 5:30 PM
      date.text = App.dateFormat.format(it.createdAt)
    }
  }
}