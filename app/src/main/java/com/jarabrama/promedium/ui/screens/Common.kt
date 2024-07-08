package com.jarabrama.promedium.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jarabrama.promedium.R
import com.jarabrama.promedium.ui.theme.big
import com.jarabrama.promedium.ui.theme.bigPadding
import com.jarabrama.promedium.ui.theme.extraLarge
import com.jarabrama.promedium.ui.theme.iconPadding
import com.jarabrama.promedium.ui.theme.nonePadding
import com.jarabrama.promedium.ui.theme.normal
import com.jarabrama.promedium.ui.theme.normalPadding
import com.jarabrama.promedium.ui.theme.smallBorder
import com.jarabrama.promedium.ui.theme.smallPadding


@Preview(showSystemUi = true)
@Composable
fun TopBarPreview() {
    TopBar("courses")
}

@Composable
fun TopBar(title: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Column(
            Modifier.systemBarsPadding(),
        ) {
            Card(
                colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.padding(
                    smallPadding
                )
            ) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    Icon(painter = painterResource(R.drawable.settings), contentDescription = null)
                }
            }
            Text(
                text = title,
                fontSize = extraLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(smallPadding),
                fontWeight = FontWeight.Bold
            )
        }
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
fun AverageBar(average: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AverageCard(average = average)
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun AverageCard(average: String) {
    Card(
        modifier = Modifier.padding(bigPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        shape = RoundedCornerShape(smallBorder)
    ) {
        Row(
            modifier = Modifier.padding(smallPadding)
        ) {
            Text(text = "Average: ", fontSize = normal, modifier = Modifier.padding(smallPadding))
            Card(

                shape = RoundedCornerShape(smallBorder)
            ) {
                Text(
                    text = average, fontSize = normal, modifier = Modifier.padding(
                        smallPadding
                    )
                )
            }
        }
    }
}

@Composable
fun SaveButton(onSaved: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(.8f),
        onClick = { onSaved() },
        shape = RoundedCornerShape(smallBorder)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Text(stringResource(id = R.string.save))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Icon(painterResource(id = R.drawable.next), null)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TopAppBarGradePreview() {
    TopAppBarWhitBack(title = "Programming techniques", backTile = null, onBack = {}) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWhitBack(title: String, onBack: () -> Unit, backTile: String?, onMore: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Column(Modifier.systemBarsPadding()) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton(
                    backTile = backTile,
                    onClick = { onBack() },
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Card(
                        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.padding(
                            smallPadding
                        ),
                        shape = CircleShape,
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        onClick = { onMore() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = null,
                            modifier = Modifier.padding(iconPadding)
                        )

                    }
                }
            }
            Text(
                text = title,
                fontSize = extraLarge,
                modifier = Modifier
                    .fillMaxWidth(.78f)
                    .padding(smallPadding),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButton(backTile: String?, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(Modifier.padding(smallPadding), verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.back), contentDescription = "back")
            Text(text = backTile ?: stringResource(id = R.string.back))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBackButton() {
    DeleteButton(containerColor = MaterialTheme.colorScheme.background, onClick = {})
}


@Preview(showBackground = true)
@Composable
fun PrevieEditButton() {
    EditButton(containerColor = MaterialTheme.colorScheme.background, onClick = {})
}

@Composable
fun DeleteButton(onClick: () -> Unit, containerColor: Color) {
    Button(
        onClick = { onClick() },
        contentPadding = nonePadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Icon(painter = painterResource(id = R.drawable.delete), contentDescription = "Delete")
    }
}


@Composable
fun EditButton(onClick: () -> Unit, containerColor: Color) {
    Button(
        onClick = { onClick() },

        contentPadding = nonePadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Icon(painter = painterResource(id = R.drawable.edit), contentDescription = "Edit")
    }
}

@Composable
fun FormCourse(
    title: String,
    nameValue: String,
    creditsValue: String,
    onNameChange: (String) -> Unit,
    onCreditsChange: (String) -> Unit,
    onSaved: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(bigPadding))
        Text(
            text = stringResource(R.string.course_details),
            Modifier.fillMaxWidth(.8f),
            fontSize = big
        )
        Spacer(modifier = Modifier.padding(smallPadding))
        Text(
            text = stringResource(R.string.course_name),
            fontSize = normal,
            modifier = Modifier
                .padding(smallPadding)
                .fillMaxWidth(.8f),
            textAlign = TextAlign.Left

        )
        TextField(
            value = nameValue,
            onValueChange = { onNameChange(it) },
            Modifier.fillMaxWidth(0.8f),
            placeholder = { NamePlaceholder() },
            singleLine = true,
            maxLines = 1,
        )
        Text(
            text = stringResource(id = R.string.credits),
            fontSize = normal,
            modifier = Modifier
                .padding(smallPadding)
                .fillMaxWidth(.8f),
            textAlign = TextAlign.Left
        )
        TextField(
            value = creditsValue,
            onValueChange = { onCreditsChange(it) },
            Modifier.fillMaxWidth(0.8f),
            placeholder = { CreditPlaceholder() },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.padding(normalPadding))
        SaveButton(onSaved = { onSaved() })

    }

}

@Composable
fun CreditPlaceholder() {
    Row {
        Text(stringResource(R.string.credit))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Icon(painterResource(id = R.drawable.credits), null)
        }
    }
}

@Composable
fun NamePlaceholder() {
    Row() {
        Text(stringResource(R.string.name))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Icon(painterResource(id = R.drawable.edit_android), null)
        }
    }
}

@Preview
@Composable
fun PreviewButtonBar() {
    NavigationButtonBar({}, {})
}

@Composable
fun NavigationButtonBar(
    onCourses: () -> Unit,
    onGrades: () -> Unit,
    courseState: Boolean,
    GradeState: Boolean
) {
    Card(
        shape = RectangleShape
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            val courseButton = Button(
                onClick = { onCourses() },
                modifier = Modifier.padding(smallPadding)
            ) {
                Icon(painter = painterResource(id = R.drawable.book), contentDescription = null)
            }

            Spacer(modifier = Modifier.padding(bigPadding))
            val gradeButton = Button(onClick = { onGrades() }, Modifier.padding(smallPadding)) {
                Icon(painter = painterResource(id = R.drawable.stack), contentDescription = null)
            }
        }

    }
}
