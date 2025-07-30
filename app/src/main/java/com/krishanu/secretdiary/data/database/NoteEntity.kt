package com.krishanu.secretdiary.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.krishanu.secretdiary.utils.getCurrentTimestamp


@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val createdAt: String = getCurrentTimestamp()
)