package com.layka.planner.ComposableFuncs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import com.layka.planner.R


@Composable
fun ColorPickerPanel(initialColor: MutableState<Color>, onClose: ()->Unit) {
    val currColor = remember {
        initialColor
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.transparent_grey))
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(10.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(colorResource(id = R.color.baby_blue))
                .padding(5.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)) {

                Text(text = "Color picked:", Modifier.align(Alignment.CenterVertically))
                Text(text = "",
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(end = 30.dp, start = 10.dp)
                        .border(
                            2.dp,
                            colorResource(id = R.color.purple_200),
                            RoundedCornerShape(100.dp)
                        )
                        .clip(RoundedCornerShape(100.dp))
                        .padding(2.dp)
                        .background(currColor.value)
                        .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                )
                Button(onClick = { onClose() }, Modifier) {
                    Text("Close")
                }
            }

            ClassicColorPicker(
                color = HsvColor.from(currColor.value),
                showAlphaBar = false,
                onColorChanged = { color: HsvColor ->
                    currColor.value = color.toColor()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .height(400.dp)
                    .width(400.dp)
            )

        }
    }
}

@Preview
@Composable
fun ColorPickerPanelPreview() {
    ColorPickerPanel(initialColor = remember {
        mutableStateOf(Color.White)
    }, onClose = fun(){})
}