package com.example.promedium.ui.theme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.promedium.R
import com.example.promedium.ui.theme.model.Course

@Composable
fun CourseUi(course: Course) {
    val paddingValues = PaddingValues(16.dp)
    Scaffold(
        topBar = { TopBar(paddingValues) },
        floatingActionButton = { Fab() },
        content = { GradesBox(paddingValues = it, course = course) }
    )
}

@Composable
private fun GradesBox(paddingValues: PaddingValues, course: Course) {
    Card(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(450.dp),
    ) {
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            GradesItems(course = course)
        }
    }
}

@Composable
fun GradesItems(course: Course) {
    GradeItem(course = course)
}

@Composable
fun GradeItem(course: Course) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "", // name of the grade
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                color = Color.White
            )
            Column(
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = "", // qualification of the grade
                    modifier = Modifier.padding(8.dp),
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    text = "", // percentage of the grade
                    modifier = Modifier.padding(8.dp),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun TopBar(paddingValues: PaddingValues) {
    Card(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {
        Text(
            text = "", // it contains the name of the course
            modifier = Modifier.padding(paddingValues),
            fontSize = 26.sp
        )

    }
}

@Composable
private fun Fab() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(painter = painterResource(id = R.drawable.add), contentDescription = "Add new note")
    }
}