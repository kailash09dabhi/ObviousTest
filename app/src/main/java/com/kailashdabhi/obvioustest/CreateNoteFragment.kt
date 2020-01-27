package com.kailashdabhi.obvioustest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kailashdabhi.obvioustest.base.Status.ERROR
import com.kailashdabhi.obvioustest.base.Status.LOADING
import com.kailashdabhi.obvioustest.base.Status.SUCCESS
import com.kailashdabhi.obvioustest.base.hideKeyboard
import com.kailashdabhi.obvioustest.data.database.Note
import kotlinx.android.synthetic.main.fragment_note_create.editContent
import kotlinx.android.synthetic.main.fragment_note_create.editTitle
import kotlinx.android.synthetic.main.fragment_note_create.save

/**
 * @author kailash09dabhi@gmail.com
 * @date 27, Jan 2020 (11:18)
 */
class CreateNoteFragment : Fragment() {
  companion object {
    fun newInstance() = CreateNoteFragment()
  }

  private val viewModel by viewModels<NoteViewModel>()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_note_create, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(false)
    save.setOnClickListener {
      activity?.hideKeyboard()
      val insertNoteLiveData = viewModel.insertNote(
        Note(title = editTitle.text.toString(), content = editContent.text.toString())
      )
      insertNoteLiveData.observe(viewLifecycleOwner, Observer {
        when (it.status) {
          SUCCESS -> {
            Toast.makeText(context, "Note created!", Toast.LENGTH_SHORT).show()
            insertNoteLiveData.removeObservers(this)
          }
          ERROR -> {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            insertNoteLiveData.removeObservers(this)
          }
          LOADING -> {
          }
        }
      })
    }
  }
}