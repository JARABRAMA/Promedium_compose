package com.example.promedium.ui.theme.ui.screens.comon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.promedium.ui.theme.ui.theme.bigSize
import com.example.promedium.ui.theme.ui.theme.extraLargePadding

@Composable
fun TopBar(title: String, textAlign: TextAlign) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            text = title,
            textAlign = textAlign,
            fontSize = bigSize,
            modifier = Modifier
                .padding(extraLargePadding)
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
fun Preview() {

}