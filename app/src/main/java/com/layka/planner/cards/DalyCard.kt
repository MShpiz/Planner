package com.layka.planner.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.layka.planner.R

@Composable
fun DailyCard(task: String, done: Boolean) {
    BaseCard(
        task,
        done,
        backgroundColor = colorResource(R.color.baby_blue),
        tagText = stringResource(id = R.string.daily_tag_text),
        tagColor = colorResource(R.color.light_blue)
    )
}

@Composable
@Preview
fun DalyCardPreview() {
    Column {
        DailyCard(
            task = "TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText",
            done = false
        )
        DailyCard(task = "Text", done = true)
    }
}