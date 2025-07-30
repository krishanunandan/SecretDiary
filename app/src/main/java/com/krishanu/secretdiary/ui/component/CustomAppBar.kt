package com.krishanu.secretdiary.ui.component



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithIconAndTitle(modifier: Modifier, title: String,icon:ImageVector, onClickAction: (() -> Unit)? = null) {
    TopAppBar(
        modifier = modifier.shadow(elevation = 4.dp),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = title,color=MaterialTheme.colorScheme.onBackground)
            }

        },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = { onClickAction?.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Menu"
                    )
                }
            }
        }, colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor =  MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor =  MaterialTheme.colorScheme.onBackground
        )
    )
}