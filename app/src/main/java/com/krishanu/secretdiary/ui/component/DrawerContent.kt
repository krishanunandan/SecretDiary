package com.krishanu.secretdiary.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krishanu.secretdiary.ui.model.NavigationItem


@Composable
fun DrawerContent(clickAction: ((selectedItemPosition: Int) -> Unit)? = null) {
    ModalDrawerSheet {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "FileIcon",
                    modifier = Modifier.size(75.dp)
                )
                Text(text = "Secret Diary", fontSize = 18.sp)
            }

        }
        HorizontalDivider()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        navigationItems.forEachIndexed { index, item ->
            NavigationDrawerItem(label = { Text(text = item.title) },
                selected = false,
                onClick = {
                    selectedItemIndex = index
                    clickAction?.invoke(index)
                },
                icon = {
                    Icon(imageVector = if (index == selectedItemIndex) {
                        item.selectedIcon!!
                    } else item.unselectedIcon!!, contentDescription = "NavigationIcon")
                }
            )
        }

    }
}


val navigationItems = listOf(
    NavigationItem(title = "Settings", Icons.Default.Settings, Icons.Filled.Settings),
    NavigationItem(title = "Lock App", Icons.Default.Lock, Icons.Filled.Lock),
    NavigationItem(title = "App Info", Icons.Default.Info, Icons.Filled.Info)
)
