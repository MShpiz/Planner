package com.layka.planner.ComposableFuncs.cards

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun BaseCard(
    task: String,
    done: Boolean,
    doneDate: LocalDate?,
    backgroundColor: Color,
    tagList: List<Pair<String, Color>> = listOf(),
    updateChecked: () -> Unit,
    typeText: String? = null
) {
    val isDone: MutableState<Boolean> =
        remember { mutableStateOf(done) }
    val isFullyShown: MutableState<Boolean> =
        remember {
            mutableStateOf(false)
        }
    Column(
        modifier = Modifier
            .padding(end = 10.dp, bottom = 2.dp, start = 5.dp, top = 5.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp))
            .padding(bottom = 2.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .padding(start = 3.dp, end = 10.dp)
            .fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        )
        {

            Checkbox(checked = isDone.value, onCheckedChange = { newDone ->
                isDone.value = newDone
                Log.v("ClickTrack", "clicked the checkBox ${isDone.value}")
                updateChecked()
            })

            Text(
                text = task,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = if (!isDone.value) {
                    TextStyle(
                        textDecoration = TextDecoration.None,
                        color = getTextColor(backgroundColor)
                    )
                } else {
                    TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        color = getTextColor(backgroundColor)
                    )
                },

                modifier = Modifier
                    .weight(1F)
                    .padding(end = 10.dp)
            )

            tagList.forEach {
                Tag(it.first, it.second)
            }
        }

        if (doneDate != null || typeText != null){
            Row(Modifier.padding(start = 50.dp, bottom = 5.dp)) {
                if (typeText != null) {
                    Text(text = typeText, style = TextStyle(color = Color.LightGray, fontSize = 12.sp))
                }
                if (doneDate != null && typeText != null)
                    Text(text = " | ", style = TextStyle(color = Color.LightGray, fontSize = 12.sp))
                if (doneDate != null) {
                    Text(text = doneDate.toString(), style = TextStyle(color = Color.LightGray, fontSize = 12.sp))
                }
            }
        }

    }
}

fun getTextColor(color: Color): Color{
    return if ((color.red*0.299 + color.green*0.587 + color.blue*0.114) > 0.6) Color.Black else Color.White
}

@Composable
fun Tag(text:String, color: Color) {
    val textColor = getTextColor(color)
    Text( // tag
        text = text,
        style = TextStyle(color=textColor),
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(color)
            .padding(start = 8.dp, end = 8.dp, bottom = 3.dp, top = 1.dp)
    )
}