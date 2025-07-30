package com.krishanu.secretdiary.ui.model

import com.krishanu.secretdiary.data.database.NoteEntity

data class AddNote(
    val title: String,
    val description: String,
)

fun AddNote.toNoteEntity(): NoteEntity {
    return NoteEntity(
        title = this.title,
        description = this.description
    )
}

