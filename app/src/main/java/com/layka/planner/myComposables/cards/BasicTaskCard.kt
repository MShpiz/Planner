package com.layka.planner.myComposables.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.layka.planner.R

@Composable
fun BasicCard(task: String, done: Boolean /*, type: cardType*/) {
    val isDone: MutableState<Boolean> =
        remember { mutableStateOf(done) }
    val isFullyShown: MutableState<Boolean> =
        remember {
            mutableStateOf(false)
        }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 10.dp, bottom=2.dp, start=5.dp, top=5.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp))
            .padding(bottom=2.dp)
            .clip(RoundedCornerShape(5.dp))


            .background(colorResource(getBackgroundColor(/*type*/)))
            .clickable {
                isFullyShown.value = !isFullyShown.value
            }
            .padding(start = 3.dp, end = 10.dp)
            .fillMaxWidth()
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
            style = if (!isDone.value) {TextStyle(textDecoration = TextDecoration.None)} else {
                TextStyle(textDecoration = TextDecoration.LineThrough)
            },

            modifier = Modifier
                .weight(1F)
                .padding(end = 10.dp)
            )
        Text(
            text = "type",
            style = TextStyle(color=colorResource(R.color.white)),
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(colorResource(id = getPlackColor(/*type*/)))
                .padding(start = 8.dp, end = 8.dp, bottom = 3.dp, top = 1.dp)


        )
    }
}

// временная заглушка пока нет типа TaskDto
private fun getPlackColor(/*type: cardType*/): Int {
    return R.color.purple_200
}

private fun getBackgroundColor(/*type: cardType*/): Int {
    return R.color.white
}

@Composable
@Preview
fun BasicCardPreview() {
    Column {
        BasicCard(task = "TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText", done = false)
        BasicCard(task = "Text", done = true)

    }
}