package com.example.promedium.ui.theme.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.promedium.ui.theme.ui.screens.comon.TopBar

@Composable
fun NewGradeUi(){

        Box (
            modifier = Modifier
                .fillMaxSize()
        ){
            CardContents()
        }

}

@Composable
fun CardContents() {
    Card(
        modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.5f)


    ){

    }
}
@Composable
@Preview
fun preview(){
    CardContents()
}