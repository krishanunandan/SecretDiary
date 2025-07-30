package com.krishanu.secretdiary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishanu.secretdiary.data.repository.NoteRepository
import com.krishanu.secretdiary.ui.model.AddNote
import com.krishanu.secretdiary.ui.model.toNoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _dialogMessage = MutableStateFlow("")
    val dialogMessage: StateFlow<String> = _dialogMessage

    val notes = repository.getNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addNote(note: AddNote) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.addNote(note.toNoteEntity())
            _isLoading.value = false
            _dialogMessage.value = "Note added successfully"
            _isSuccess.value = true
        }
    }

    fun updateNote(noteID: Int,title:String,description:String) {
        viewModelScope.launch {
            repository.updateNote(noteID,title,description)
            _dialogMessage.value = "Note updated successfully"
            _isSuccess.value = true
        }
    }

    fun deleteNote(noteID: Int) {
        viewModelScope.launch {
            repository.deleteNote(noteID)
            _dialogMessage.value = "Note deleted successfully"
            _isSuccess.value = true
        }

    }

    fun resetSuccessFlag() {
        _isSuccess.value = false
    }

    fun updateSuccessFlag(status: Boolean) {
        _isSuccess.value = status
    }


    fun updateDialogMessage(message: String) {
        _dialogMessage.value = message
    }

}