package com.krishanu.secretdiary.ui.model

import com.krishanu.secretdiary.data.database.NoteEntity

data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val createdAt: String
)

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        description = this.description,
        createdAt = this.createdAt
    )
}


