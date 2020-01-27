package com.kailashdabhi.obvioustest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kailashdabhi.obvioustest.base.Resource
import com.kailashdabhi.obvioustest.base.Status.ERROR
import com.kailashdabhi.obvioustest.base.Status.LOADING
import com.kailashdabhi.obvioustest.base.Status.SUCCESS
import com.kailashdabhi.obvioustest.data.database.Note
import kotlinx.android.synthetic.main.fragment_notes.progress
import kotlinx.android.synthetic.main.fragment_notes.recyclerView

/**
 * @author kailash09dabhi@gmail.com
 * @date 22, Jan 2020 (11:18)
 */
class NoteListFragment : Fragment() {
  companion object {
    fun newInstance() = NoteListFragment()
  }

  private val viewModel by viewModels<NoteViewModel>()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_notes, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
    viewModel.notes().observe(viewLifecycleOwner, Observer {
      when (it.status) {
        SUCCESS -> {
          showList(it)
        }
        ERROR -> {
          Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
          showList(it)
        }
        LOADING -> {
          progress.visibility = View.VISIBLE
          recyclerView.visibility = View.GONE
        }
      }
    })
  }

  private fun showList(resource: Resource<List<Note>>) {
    progress.visibility = View.GONE
    recyclerView.visibility = View.VISIBLE
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = NoteAdapter(resource.data ?: listOf())
  }

  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.fragment_notes, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_addNote -> {
        parentFragmentManager.beginTransaction()
          .replace(R.id.fragmentContainer, CreateNoteFragment.newInstance()).addToBackStack(null)
          .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
          .commit()
      }
    }
    return true
  }
}