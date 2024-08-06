package com.layka.planner.myComposables.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicCard(task: String, isDone: Boolean) {
    val isDone: MutableState<Boolean> =
        remember { mutableStateOf(isDone) }
    val isFullyShown: MutableState<Boolean> =
        remember {
            mutableStateOf(false)
        }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                isFullyShown.value = !isFullyShown.value
            }
            .padding(bottom = 5.dp, end = 10.dp)
    )
    {

        Checkbox(checked = isDone.value, onCheckedChange = {
            newDone -> isDone.value = newDone
        } )
        Text(
            text = task,
            fontSize = 15.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = if (isFullyShown.value) {Int.MAX_VALUE} else {1},
            )
    }
}

@Composable
@Preview()
fun BasicCardPreview() {
    Column {
        BasicCard(task = "TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText", isDone = false)
        BasicCard(task = "Text", isDone = false)

    }
}