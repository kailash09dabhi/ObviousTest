package com.kailashdabhi.obvioustest.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Note")
data class Note(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  val id: Long = 0,
  @ColumnInfo(name = "title")
  val title: String,
  @ColumnInfo(name = "content")
  val content: String,
  @ColumnInfo(name = "created_at")
  val createdAt: Long = System.currentTimeMillis(),
  @ColumnInfo(name = "updated_at")
  val updatedAt: Long = System.currentTimeMillis()
) : Parcelable
