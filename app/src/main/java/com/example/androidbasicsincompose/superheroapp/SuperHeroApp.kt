package com.example.androidbasicsincompose.superheroapp

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuperHeroApp() {

    val heroList by remember { mutableStateOf(value = HeroesRepository.heroes) }
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }


    Scaffold(
        topBar = {
            SuperHeroAppBar()
        }
    ) {

        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy
                )
            ),
            exit = fadeOut()
        ) {

            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(heroList) { index, hero ->
                    HeroItem(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = StiffnessVeryLow,
                                        dampingRatio = Spring.DampingRatioLowBouncy
                                    ),
                                    initialOffsetY = {it * (index + 1)}
                                )
                            ),
                        heroName = hero.nameRes,
                        heroDesc = hero.descriptionRes,
                        heroImage = hero.imageRes
                    )
                }
            }
        }
    }
}

@Composable
fun HeroItem(
    modifier:Modifier = Modifier,
    @StringRes heroName: Int,
    @StringRes heroDesc: Int,
    @DrawableRes heroImage: Int
) {

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.surface)
                .sizeIn(72.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = heroName),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(id = heroDesc),
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Clip
                )

            }
            HeroImage(
                heroImage = heroImage,
                heroDesc = heroDesc
            )
        }
    }
}

@Composable
fun HeroImage(
    modifier: Modifier = Modifier,
    @DrawableRes heroImage: Int,
    @StringRes heroDesc: Int
) {

    val painterResource = painterResource(id = heroImage)

    Box(
        modifier = Modifier
            .size(72.dp)
            .clip(shape = RoundedCornerShape(4.dp)),
    ) {
        Image(
            painter = painterResource,
            contentDescription = stringResource(id = heroDesc),
            contentScale = ContentScale.FillWidth
        )
    }


}

@Composable
fun SuperHeroAppBar() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .height(48.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Superheroes",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }
}





















