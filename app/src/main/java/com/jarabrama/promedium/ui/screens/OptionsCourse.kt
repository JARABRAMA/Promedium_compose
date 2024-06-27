package com.jarabrama.promedium.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.littleElevation
import com.jarabrama.promedium.ui.theme.smallPadding

@Composable
fun OptionsCourse(
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onBack: () -> Unit,
    courseName: String
) {
    Dialog(onDismissRequest = { onBack() }) {
        Content(onDelete = { onDelete() }, onEdit = { onEdit() }, courseName)
    }
}

@Preview
@Composable
fun PreviewContent() {
    Content(onDelete = { }, onEdit = {}, courseName = "Techniques of programming")
}

@Composable
fun Content(onDelete: () -> Unit, onEdit: () -> Unit, courseName: String) {
    val color = MaterialTheme.colorScheme.primaryContainer
    Card(
        colors = CardDefaults.cardColors(
            contentColor = color
        ),
    ) {
        ButtonPopUp(
            onClick = { onDelete() },
            stringResource(R.string.delete),
            R.drawable.delete_android,
            courseName = courseName,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        )
        ButtonPopUp(
            onClick = { onEdit() },
            message = stringResource(id = R.string.edit),
            drawable = R.drawable.edit_android,
            courseName = courseName,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun ButtonPopUp(
    onClick: () -> Unit,
    message: String,
    drawable: Int,
    courseName: String,
    containerColor: Color,
    contentColor: Color
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(smallPadding)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = littleElevation
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.padding(
                    smallPadding
                )
            )
            Text(
                text = "$message $courseName", modifier = Modifier.padding(
                    smallPadding
                )
            )
        }
    }
}