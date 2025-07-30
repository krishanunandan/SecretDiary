package com.krishanu.secretdiary.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.krishanu.secretdiary.navigation.NavigationScreen
import com.krishanu.secretdiary.ui.model.Note
import com.krishanu.secretdiary.ui.viewmodel.HomeViewModel
import com.krishanu.secretdiary.utils.getTimeAgo
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeContent(navController: NavController, paddingValues: PaddingValues) {
    val homeViewModel: HomeViewModel = getViewModel()
    //val notes = homeViewModel.notes
    val notes by homeViewModel.notes.collectAsState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notes.size) { index ->
            NotesItem(notes[index], onItemClick = { note ->
                navController.navigate(
                    NavigationScreen.CreateNote.passArgs(
                        noteID = note.id,
                        type = "Update",
                        title = note.title,
                        description = note.description
                    )
                )
            })
        }
    }
}

@Composable
fun NotesItem(addNote: Note, onItemClick: ((note: Note) -> Unit)) {
    Box(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(20.dp)
            .clickable {
                onItemClick.invoke(addNote)
            }

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = addNote.title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = addNote.description,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = getTimeAgo(addNote.createdAt),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.End),
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    NotesItem(
        addNote = Note(
            1,
            "Hello",
            description = "I m krishanu Nandan fb gb ghgh gbfgnb ",
            "12-July-2025"
        ), onItemClick = {}
    )
}

