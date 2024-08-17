package com.layka.planner.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.layka.planner.R

@Composable
fun PlainCard(task: String, done: Boolean) {
    BaseCard(
        task,
        done,
        backgroundColor = colorResource(R.color.white)
    )
}