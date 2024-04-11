package com.example.promedium.ui.theme.ui.screens.comon

import android.text.InputType
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.promedium.R

@Composable
fun TopBar(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    paddingValues: PaddingValues
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        shape = MaterialTheme.shapes.large,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black
        ),
        modifier = Modifier
            .padding(paddingValues),
        singleLine = true

    )
}

@Composable
@Preview
fun preview() {
    TopBar(title = "Pormediana")
}