package com.krishanu.secretdiary.data.repository

import com.krishanu.secretdiary.data.database.NoteDao
import com.krishanu.secretdiary.data.database.NoteEntity
import com.krishanu.secretdiary.ui.model.Note
import com.krishanu.secretdiary.ui.model.toNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepository(private val noteDao: NoteDao) {

    fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }
    suspend fun addNote(note: NoteEntity) = noteDao.addNote(note)

    suspend fun updateNote(noteID: Int,title:String,description:String){
        val note = noteDao.getNoteById(noteID)
        if (note != null) {
            val updatedNote = note.copy(
                title = title,
                description = description
            )
            noteDao.updateNote(updatedNote)
        }
    }
    suspend fun deleteNote(noteID: Int) = noteDao.deleteNote(noteID)
}