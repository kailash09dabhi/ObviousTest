package com.kailashdabhi.obvioustest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kailashdabhi.obvioustest.base.BaseFragment
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
class CreateNoteFragment : BaseFragment() {
  companion object {
    fun newInstance() = CreateNoteFragment()
  }

  private val viewModel by viewModels<NoteViewModel>()

  override fun layoutId() = R.layout.fragment_note_create

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.title = "Create note"
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
            it.data?.let { note ->
              parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, NoteDetailFragment.newInstance(note))
                .addToBackStack(null)
                .commit()
            }
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