package com.krishanu.secretdiary.ui.screens


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krishanu.secretdiary.ui.component.AddUpdateNoteContent
import com.krishanu.secretdiary.ui.component.AppBarWithIconAndTitle
import com.krishanu.secretdiary.ui.component.ShowMessageDialog
import com.krishanu.secretdiary.ui.model.AddNote
import com.krishanu.secretdiary.ui.theme.SecretDiaryTheme
import com.krishanu.secretdiary.ui.viewmodel.HomeViewModel
import com.krishanu.secretdiary.utils.validateNote
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateNoteScreen(
    navController: NavController,
    type: String,
    noteID: Int,
    title: String,
    description: String
) {
    SecretDiaryTheme {
        val homeViewModel: HomeViewModel = getViewModel()

        val isLoading by homeViewModel.isLoading.collectAsState()
        val isSuccess by homeViewModel.isSuccess.collectAsState()
        val dialogMessage by homeViewModel.dialogMessage.collectAsState()

        var noteTitle by rememberSaveable { mutableStateOf("") }
        var noteDescription by rememberSaveable { mutableStateOf("") }

        val headerTitle = if (type == "Add") {
            "Add Note"
        } else {
            "Update Note"
        }
        if (type != "Add") {
            noteTitle = title
            noteDescription = description
        }

        // Show loading dialog
        if (isLoading) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Saving Note") },
                text = { Text("Please wait...") },
                confirmButton = {}
            )
        }

        // Show success dialog
        if (isSuccess) {
            ShowMessageDialog(
                message = dialogMessage,
                onDismissRequest = {
                    if (dialogMessage.contains("required")) {
                        homeViewModel.resetSuccessFlag()
                    } else {
                        homeViewModel.resetSuccessFlag()
                        navController.popBackStack()
                    }

                }
            )

        }



        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                AppBarWithIconAndTitle(
                    modifier = Modifier
                        .fillMaxWidth(), headerTitle, Icons.Filled.ArrowBack
                ) {
                    navController.popBackStack()
                }
            }) { innerPadding ->
            AddUpdateNoteContent(
                isEditMode = type != "Add",
                title = noteTitle,
                onTitleChanged = { noteTitle = it },
                description = noteDescription,
                onDescriptionChanged = { noteDescription = it },
                paddingValues = innerPadding,
                onSaveClickAction = {
                    val (error, message) = validateNote(
                        title = noteTitle,
                        description = noteDescription
                    )
                    if (error) {
                        homeViewModel.updateDialogMessage(message)
                        homeViewModel.updateSuccessFlag(true)
                    } else {
                        if (type != "Add") {
                            homeViewModel.updateNote(noteID, noteTitle, noteDescription)
                        } else {
                            homeViewModel.addNote(AddNote(noteTitle, noteDescription))
                        }
                    }

                },
                onDeleteClickAction = {
                    homeViewModel.deleteNote(noteID)
                }
            )
        }
    }
}


