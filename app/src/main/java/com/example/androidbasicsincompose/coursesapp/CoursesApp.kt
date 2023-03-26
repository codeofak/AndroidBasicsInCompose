package com.example.androidbasicsincompose.coursesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidbasicsincompose.R
import kotlin.random.Random

@Composable
fun CoursesApp() {

    val list by remember { mutableStateOf(DataSource.topics) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(3.dp)
    ) {
        items(list) {
            CourseCard(course = it)
        }
        items(list) {
            CourseCard(course = it)
        }
    }

}

@Composable
fun CourseCard(course: Topic) {

    val courseName = stringResource(id = course.courseName)
    val associatedCourses = course.associatedCourses.toString()

    Card(
        elevation = 3.dp
    ) {
        Row() {
            CoursesImage(course = course)

            Column() {
                Text(
                    text = courseName,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 13.dp, end = 14.dp, bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = "Grain icon",

                        )
                    Text(
                        text = associatedCourses,
                        style = MaterialTheme.typography.caption,

                        )
                }
            }
        }
    }
}

@Composable
fun CoursesImage(
    course: Topic
) {

    val image = painterResource(id = course.courseImage)

    Image(
        painter = image,
        contentDescription = stringResource(id = course.courseName),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .aspectRatio(1f)
    )
}



