package com.kailashdabhi.obvioustest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kailashdabhi.obvioustest.NoteAdapter.ViewHolder
import com.kailashdabhi.obvioustest.data.database.Note
import kotlinx.android.synthetic.main.item_note.view.content
import kotlinx.android.synthetic.main.item_note.view.createdAt
import kotlinx.android.synthetic.main.item_note.view.title

class NoteAdapter(val items: List<Note>) :
  RecyclerView.Adapter<ViewHolder>() {

  override fun getItemCount(): Int {
    return items.size
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    val note = items[position]
    holder.title.text = note.title
    holder.content.text = note.content
    holder.createdAt.text = note.createdAt.toString()
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.title
    val content = view.content
    val createdAt = view.createdAt
  }
}
