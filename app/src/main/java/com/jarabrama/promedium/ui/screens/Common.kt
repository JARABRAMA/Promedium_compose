package com.jarabrama.promedium.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.bigPadding
import com.jarabrama.promedium.ui.theme.normal

import com.jarabrama.promedium.ui.theme.smallPadding

@Composable
fun TopBar(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(25),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = CircleShape,
        colors = with(TextFieldDefaults) {
            colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            )
        },
        placeholder = {
            Text(text = placeholder, fontSize = normal)
        },
        singleLine = true,

        )
}


@Preview
@Composable
fun TextInputPreview() {
    TextInput(
        placeholder = "Placeholder",
        value = "",
        onValueChange = {},
        keyboardType = KeyboardType.Text
    )
}

@Preview
@Composable
fun ButtonPreview() {
    FloatingButton {

    }
}


@Composable
fun FloatingButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        contentPadding = smallPadding,
        modifier = Modifier
            .height(60.dp)
            .width(60.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "add new note",
            modifier = Modifier
                .fillMaxSize(1f)
        )
    }
}

@Composable
fun AverageBar(average: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AverageCard(average = average)
        }
    }
}


@Composable
fun AverageCard(average: Double) {
    Card(
        modifier = Modifier.padding(bigPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(smallPadding)
        ) {
            Text(text = "Average: ", fontSize = normal, modifier = Modifier.padding(smallPadding))
            Card(
                colors = CardDefaults.cardColors(
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Text(
                    text = average.toString(), fontSize = normal, modifier = Modifier.padding(
                        smallPadding
                    )
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun Preview() {
    AverageCard(average = 3.4)
}
