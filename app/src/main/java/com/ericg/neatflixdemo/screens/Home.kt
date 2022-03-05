package com.ericg.neatflixdemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ericg.neatflixdemo.R
import androidx.compose.animation.AnimatedVisibility as Visibility

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF180E36))
    ) {
        SearchBarAndProfileImage()
        NestedScroll()
    }
}

@Composable
fun SearchBarAndProfileImage() {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(0.82F)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0XFF423460))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(34.dp),
                    tint = Color.White.copy(alpha = 0.48F),
                    contentDescription = "search icon"
                )
                Text(
                    text = "Search...",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White.copy(alpha = 0.48F)
                )
                DropDownMenu()
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            contentDescription = "profile image"
        )
    }
}

@Composable
fun DropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Movies", "Series")
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF180E36))
    ) {
        Text(
            text = items[selectedIndex],
            color = Color.White.copy(alpha = 0.78F),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp)
                .clickable { expanded = !expanded }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0XFF423460))
        ) {
            items.forEachIndexed { index, value ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedIndex = index
                    }) {
                    Text(
                        text = value,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    if (index != items.lastIndex) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .height(0.5.dp)
                                .background(Color(0XFF9495B1))
                        )
                    }
                }

            }

        }

    }

}

@Composable
fun NestedScroll() {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        item {
            Text(
                text = "Genre",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.78F),
                modifier = Modifier.padding(4.dp)
            )
        }

        item {
            val genres = listOf("All", "Drama", "Romance", "Horror", "Sci-Fi")
            var selectedGenre by remember { mutableStateOf("All") }

            LazyRow(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                items(count = genres.size) {
                    GenreChip(
                        genre = genres[it],
                        selected = genres[it] == selectedGenre
                    ) {
                        selectedGenre = genres[it]
                    }
                }
            }
        }

        item {
            Text(
                text = "Top 10",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.78F),
                modifier = Modifier.padding(4.dp)
            )
        }
        item {
            LazyRow() {
                items(count = 10) {
                    MovieItem(
                        image = painterResource(id = R.drawable.android_devs),
                        title = "Android Devs",
                        modifier = Modifier
                            .width(255.dp)
                            .height(150.dp),
                        landsScape = true
                    )
                }
            }
        }
        item {
            Text(
                text = "Recent",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.78F),
                modifier = Modifier.padding(4.dp)
            )
        }
        item {
            LazyRow() {
                items(count = 10) {
                    MovieItem(
                        image = painterResource(id = R.drawable.android_devs),
                        title = "",
                        modifier = Modifier
                            .width(122.dp)
                            .height(180.dp)
                    )
                }
            }
        }
        item {
            Text(
                text = "Recommended",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.78F),
                modifier = Modifier.padding(4.dp)
            )
        }
        item {
            LazyRow() {
                items(count = 10) {
                    MovieItem(
                        image = painterResource(id = R.drawable.android_devs),
                        title = "",
                        modifier = Modifier
                            .width(122.dp)
                            .height(180.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun GenreChip(genre: String, selected: Boolean, onClick: () -> Unit) {
    // Chip
    Box(
        modifier = Modifier
            .padding(end = 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                color = if (selected) Color(0XFF9495B1)
                else Color(0XFF423460).copy(alpha = 0.46F)
            )
            .height(36.dp)
            .widthIn(min = 80.dp)
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = genre,
            modifier = Modifier
                .align(Alignment.Center),
            color = if (selected) Color(0XFF180E36)
            else Color.White
        )
    }
}

@Composable
fun MovieItem(
    image: Painter,
    title: String,
    modifier: Modifier,
    landsScape: Boolean = false
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = "image",
            contentScale = Crop,
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
        )
        Visibility(visible = landsScape) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                color = Color.White.copy(alpha = 0.78F)
            )
        }

    }

}

@Preview
@Composable
fun HoePrev() {
    Home()
}