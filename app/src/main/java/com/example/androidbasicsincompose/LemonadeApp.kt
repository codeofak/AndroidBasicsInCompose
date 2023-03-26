package com.example.androidbasicsincompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LamonadeAppComposable(){

    var currentStep by remember{ mutableStateOf(1) }

    var squeezCount by remember{ mutableStateOf(0) }




    when (currentStep) {
        1 -> {
            lemonAppTextAndImage(
                textLabelRes = R.string.lemon_select,
                imageRes = R.drawable.lemon_tree,
                contentDesRes = R.string.lemon_tree_content_description ,
                onImageClick = {
                    currentStep = 2
                    squeezCount = (2..6).random()
                })
        }

        2 -> {
            Text(
                text = squeezCount.toString(),
                modifier = Modifier
                    .wrapContentSize(Alignment.TopCenter)
            )
            lemonAppTextAndImage(
                textLabelRes = R.string.lemon_squeeze,
                imageRes = R.drawable.lemon_squeeze,
                contentDesRes =R.string.lemon_content_description ,
                onImageClick = {

                    squeezCount--
                    if (squeezCount == 0){
                        currentStep = 3
                    }

                })
        }

        3 -> {
            lemonAppTextAndImage(
                textLabelRes = R.string.lemon_drink,
                imageRes = R.drawable.lemon_drink,
                contentDesRes = R.string.lemonade_content_description,
                onImageClick = { currentStep = 4 })
        }

        4 -> {
            lemonAppTextAndImage(
                textLabelRes = R.string.lemon_empty_glass,
                imageRes = R.drawable.lemon_restart,
                contentDesRes = R.string.empty_glass_content_description,
                onImageClick = { currentStep = 1 })
        }
    }




}

@Composable
fun lemonAppTextAndImage(
    textLabelRes: Int,
    imageRes: Int,
    contentDesRes: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = textLabelRes),
            fontSize = 18.sp
            )
        Spacer(modifier = Modifier.size(16.dp))
        
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = contentDesRes),
            modifier = Modifier
                .wrapContentSize()
                .clickable { onImageClick() }
                .border(2.dp, color = Color(105, 205, 216))
                .padding(16.dp)
        )
    }
}