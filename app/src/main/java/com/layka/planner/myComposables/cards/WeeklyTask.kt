package com.layka.planner.myComposables.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.layka.planner.R

@Composable
fun WeeklyCard(task: String, done: Boolean) {
    BasicCard(
        task,
        done,
        backgroundColor = colorResource(R.color.light_mint),
        tagText = stringResource(id = R.string.weekly_tag_text),
        tagColor = colorResource(R.color.mint)
    )
}

@Composable
@Preview
fun WeeklyCardPreview() {
    Column {
        WeeklyCard(
            task = "TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText",
            done = false
        )
        WeeklyCard(task = "Text", done = true)
    }
}