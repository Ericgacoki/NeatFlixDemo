package com.ericg.neatflixdemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ericg.neatflixdemo.R
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MovieDetails() {
    val movieGenres = remember {
        mutableStateListOf("Sci-fi", "Drama", "Fantasy", "Android")
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF180E36))
    ) {
        val (
            headerImage,
            backButton,
            movieGenreChips,
            descriptionText,
            castTitle,
            cast,
            movieTitleBox,
            moviePosterImage,
            translucentBr,
            ratingBar
        ) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.dont_look_up),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .fillMaxSize(0.33F)
                .constrainAs(headerImage) {
                    top.linkTo(parent.top)
                },
            contentScale = ContentScale.Crop,
            contentDescription = "header image"
        )
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .background(
                    shape = CircleShape,
                    color = Color(0XFF423460).copy(alpha = 0.78F)
                )
                .constrainAs(backButton) {
                    top.linkTo(headerImage.top)
                    start.linkTo(headerImage.start)
                },
            contentDescription = "Back button"
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0XFF180E36).copy(alpha = 0.5F),
                            Color(0xFF180E36)
                        ),
                        startY = 0F
                    )
                )
                .constrainAs(translucentBr) {
                    bottom.linkTo(headerImage.bottom)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.look_up_portrait),
            modifier = Modifier // 2*3 ratio
                .padding(all = 16.dp)
                .height(195.dp)
                .width(130.dp)
                .constrainAs(moviePosterImage) {
                    top.linkTo(headerImage.bottom)
                    bottom.linkTo(headerImage.bottom)
                }
                .clip(RoundedCornerShape(size = 4.dp)),
            contentDescription = null)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .constrainAs(movieGenreChips) {
                    top.linkTo(moviePosterImage.bottom, margin = 4.dp)
                }
        ) {
            movieGenres.forEach { text ->
                item {
                    GenreChip(genre = text)
                }
            }
        }
        Text(
            text = "Don't Look Up is a 2021 American apocalyptic black comedy film written, produced, and directed by Adam McKay, and starring an ensemble cast including Leonardo DiCaprio, Jennifer Lawrence, Rob Morgan, Jonah Hill, Mark",
            color = White,
            maxLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .constrainAs(descriptionText) {
                    top.linkTo(movieGenreChips.bottom)
                }
        )
        Text(
            modifier = Modifier
                .constrainAs(castTitle) {
                    top.linkTo(descriptionText.bottom)
                    start.linkTo(parent.start)
                }
                .padding(4.dp),
            text = "Cast",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = White
        )

        LazyRow(
            modifier = Modifier.constrainAs(cast){
                top.linkTo(castTitle.bottom)
            }.fillMaxWidth()
        ) {
            items(count = 10) {
                Cast(
                    image = R.drawable.android_devs,
                    name = "Chacha",
                    role = "Actor"
                )
            }
        }


    }


}

@Composable
fun GenreChip(genre: String) {
    Box(
        modifier = Modifier
            .padding(end = 4.dp)
            .widthIn(min = 80.dp)
            .clip(CircleShape)
            .background(Color(0XFF423460))
            .padding(all = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = genre,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun Cast(
    image: Int,
    name: String,
    role: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
    ) {
        CoilImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp),
            imageModel = image,
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = White,
                durationMillis = 350,
                dropOff = 0.65F,
                tilt = 20F
            ),
            previewPlaceholder = R.drawable.dont_look_up,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 1000)
        )
        Text(
            text = name,
            color = White,
            modifier = Modifier
                .padding(vertical = 4.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        Text(
            text = role,
            color = White,
            modifier = Modifier
                .padding(bottom = 4.dp),
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun MovieDetailsPreview() {
    MovieDetails()
}