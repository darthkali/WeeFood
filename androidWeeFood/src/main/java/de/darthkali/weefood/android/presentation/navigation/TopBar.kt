package de.darthkali.weefood.android.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String = "",
    navigationIcon: ImageVector? = null,
    navigationIconClickAction: (() -> Unit)? = null,
    actionIcon: ImageVector? = null,
    actionIconIconClickAction: (() -> Unit)? = null,
) {
    TopAppBar(
        navigationIcon = setIcon(navigationIcon, navigationIconClickAction),
        title = { Text(title) },
        actions = {
            actionIconIconClickAction?.let { it ->
                IconButton(onClick = it) {
                    actionIcon?.let { Icon(it, "") }
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
    )
}

//@Composable
fun setIcon(
    icon: ImageVector? = null,
    iconClickAction: (() -> Unit)? = null,
): @Composable() (() -> Unit)? {
    icon?.let {
        return {
            Icon(
                imageVector = it,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction?.invoke() })
                    .fillMaxHeight()
            )
        }
    }
    return null
}

//
//@Composable
//fun MainContent(){
//    val result = remember { mutableStateOf("") }
//    val expanded = remember { mutableStateOf(false)}
//    val liked = remember { mutableStateOf(true) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "Top app bar")
//                },
//
//                navigationIcon = {
//                    // show drawer icon
//                    IconButton(
//                        onClick = {
//                            result.value = "Drawer icon clicked"
//                        }
//                    ) {
//                        Icon(Icons.Filled.Menu, contentDescription = "")
//                    }
//                },
//
//                actions = {
//                    IconButton(onClick = {
//                        result.value = " Play icon clicked"
//                    }) {
//                        Icon(Icons.Filled.PlayCircle, contentDescription = "")
//                    }
//
//                    IconToggleButton(
//                        checked = liked.value,
//                        onCheckedChange = {
//                            liked.value = it
//                            if (liked.value){
//                                result.value = "Liked"
//                            }else{
//                                result.value = "Unliked"
//                            }
//                        }
//                    ) {
//                        val tint by animateColorAsState(
//                            if (liked.value) Color(0xFF7BB661)
//                            else Color.LightGray
//                        )
//                        Icon(
//                            Icons.Filled.Favorite,
//                            contentDescription = "Localized description",
//                            tint = tint
//                        )
//                    }
//
//                    Box(
//                        Modifier
//                            .wrapContentSize(Alignment.TopEnd)
//                    ) {
//                        IconButton(onClick = {
//                            expanded.value = true
//                            result.value = "More icon clicked"
//                        }) {
//                            Icon(
//                                Icons.Filled.MoreVert,
//                                contentDescription = "Localized description"
//                            )
//                        }
//
//                        DropdownMenu(
//                            expanded = expanded.value,
//                            onDismissRequest = { expanded.value = false },
//                        ) {
//                            DropdownMenuItem(onClick = {
//                                expanded.value = false
//                                result.value = "First item clicked"
//                            }) {
//                                Text("First Item")
//                            }
//
//                            DropdownMenuItem(onClick = {
//                                expanded.value = false
//                                result.value = "Second item clicked"
//                            }) {
//                                Text("Second item")
//                            }
//
//                            Divider()
//
//                            DropdownMenuItem(onClick = {
//                                expanded.value = false
//                                result.value = "Third item clicked"
//                            }) {
//                                Text("Third item")
//                            }
//
//                            Divider()
//
//                            DropdownMenuItem(onClick = {
//                                expanded.value = false
//                                result.value = "Fourth item clicked"
//                            }) {
//                                Text("Fourth item")
//                            }
//                        }
//                    }
//                },
//
//                backgroundColor = Color(0xFDCD7F32),
//                elevation = AppBarDefaults.TopAppBarElevation
//            )
//        },
//
//        content = {
//            Box(
//                Modifier
//                    .background(Color(0XFFE3DAC9))
//                    .padding(16.dp)
//                    .fillMaxSize(),
//            ) {
//                Text(
//                    text = result.value,
//                    fontSize = 22.sp,
//                    fontFamily = FontFamily.Serif,
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }
//        }
//    )
//}