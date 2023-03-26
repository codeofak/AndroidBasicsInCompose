package com.example.androidbasicsincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.androidbasicsincompose.superheroapp.SuperHeroApp
import com.example.androidbasicsincompose.ui.theme.AndroidBasicsInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicsInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    //DiceRollerApp()

                    //LamonadeAppComposable()

                    //TipTimeScreen()

                    //AffirmationApp()
                    
                    //CoursesApp()

                    //WoofApp()

                    SuperHeroApp()


                }
            }
        }
    }
}


