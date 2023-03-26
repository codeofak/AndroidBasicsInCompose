package com.example.androidbasicsincompose.affirmationapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidbasicsincompose.R
import com.example.androidbasicsincompose.affirmationapp.data.DataSource
import com.example.androidbasicsincompose.affirmationapp.model.Affirmation
import com.example.androidbasicsincompose.ui.theme.AndroidBasicsInComposeTheme

@Composable
fun AffirmationApp() {
    val context = LocalContext.current

    AffirmationList(affirmationList = DataSource().loadAffirmation())

}

@Composable
fun AffirmationList(
    affirmationList: List<Affirmation>,
    modifier: Modifier = Modifier
) {
    LazyColumn(){
        items(affirmationList){ affirmation ->
            AffirmationCard(affirmation = affirmation)
        }
    }
}


@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .wrapContentSize(Alignment.TopCenter),
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = affirmation.stringResourceId.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview
@Composable
fun AffirmationCardPreview() {

    AndroidBasicsInComposeTheme() {

        AffirmationCard(
            affirmation = Affirmation(
                stringResourceId = R.string.affirmation1,
                imageResourceId = R.drawable.image1
            )
        )
    }

}


