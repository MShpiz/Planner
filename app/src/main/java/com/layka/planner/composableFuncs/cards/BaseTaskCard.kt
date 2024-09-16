package com.layka.planner.composableFuncs.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.layka.planner.R
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
            Checkbox(
                checked = isDone.value, onCheckedChange = { newDone ->
                    isDone.value = newDone
                    //Log.v("ClickTrack", "clicked the checkBox ${isDone.value}")
                    updateChecked()
                })

            Text(
                text = task,
                fontSize = 17.sp,
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
                    .align(Alignment.CenterVertically)
            )

            tagList.forEach {
                Box(Modifier.align(Alignment.CenterVertically)) {
                    Tag(it.first, it.second)
                }
            }
        }

        
        Row(Modifier.padding(start = 50.dp, bottom = 5.dp)) {
            Text(text = stringResource(id = R.string.repeats), style = TextStyle(color = Color.Gray, fontSize = 12.sp))
            if (typeText != null) {
                Text(text = typeText, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
            } else {
                Text(text = stringResource(id = R.string.never), style = TextStyle(color = Color.Gray, fontSize = 12.sp))
            }

            if (doneDate != null) {
                VerticalDivider(
                    Modifier
                        .height(16.dp)
                        .padding(3.dp))
                Text(text = stringResource(id = R.string.done) + doneDate.toString(), style = TextStyle(color = Color.Gray, fontSize = 12.sp))
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
    val finalText = if (text.length > 7) {
        text.substring(0..6)+"..."
    } else{
        text
    }
    Text( // tag
        text = finalText,
        style = TextStyle(color=textColor),
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color)
            .padding(start = 10.dp, end = 10.dp, bottom = 3.dp, top = 3.dp)
    )
}

@Preview
@Composable
fun BaseCardPreview() {
    BaseCard(task = "AAA", done = false, doneDate = LocalDate.now(), backgroundColor = Color.White, updateChecked = {  }, typeText = "Daily", tagList = listOf(Pair("AAA", Color.Blue)))
}