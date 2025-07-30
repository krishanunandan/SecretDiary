package com.krishanu.secretdiary.utils


fun validateNote(title: String, description: String): Pair<Boolean, String> {
    var error: Boolean = false
    var message: String = ""
    if (title.isBlank() || title.isEmpty()) {
        error = true
        message = "Title required"
    } else if (description.isBlank() || description.isEmpty()) {
        error = true
        message = "Description required"
    }
    return Pair(error, message)
}