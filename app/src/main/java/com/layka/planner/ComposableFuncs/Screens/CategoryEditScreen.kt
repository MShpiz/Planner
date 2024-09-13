package com.layka.planner.ComposableFuncs.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.layka.planner.ComposableFuncs.ColorPickerPanel
import com.layka.planner.R

@Composable
fun CategoryEditScreen(navController: NavController, id: Long? = null, /*taskViewModel: TaskEditViewModel = hiltViewModel()*/) {
    val categoryName = remember {
        mutableStateOf("")
    }

    val tagColor = remember {
        mutableStateOf(Color.Red)
    }

    val catBackgroundColor = remember {
        mutableStateOf(Color.White)
    }
    val currentColorField = remember {
        mutableStateOf<Int?>(null)
    }

    val isColorPickerOpen = remember {
        mutableStateOf(false)
    }

    val closeColorPicker = fun (){
        isColorPickerOpen.value = false
        currentColorField.value = null
    }

    val openColorPicker = fun (field: Int){
        isColorPickerOpen.value = true
        currentColorField.value = field
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ){ innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .blur(if (isColorPickerOpen.value) 10.dp else 0.dp)
            ) {
                TextField(value = categoryName.value, onValueChange = { categoryName.value = it })

                ColorField("tag color: ", 1, tagColor, openColorPicker)
                ColorField("card background color: ", 2, catBackgroundColor, openColorPicker)

                Row {
                    Button(onClick = { navController.popBackStack() }) {
                        Text(text = "Save")
                    }
                }
            }
            Box(modifier = Modifier
                .matchParentSize()
                .then(
                    if (isColorPickerOpen.value) {
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {}
                        )
                    } else {
                        Modifier
                    }))

            if (isColorPickerOpen.value) {
                val initialColor = when(currentColorField.value) {
                    1 -> tagColor
                    2 -> catBackgroundColor
                    else ->  remember {
                        mutableStateOf( Color.White)
                    }
                }
                ColorPickerPanel(
                    initialColor = initialColor,
                    closeColorPicker
                )

            }
        }
    }
}

@Composable
fun ColorField(
    fieldName: String,
    filedNumber: Int,
    currentColor: MutableState<Color>,
    changeColor: (Int) -> Unit,
) {
    Row {
        Text(fieldName,
            Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 5.dp))
        Button(
            onClick = { changeColor(filedNumber) },
            colors = ButtonDefaults.buttonColors(containerColor = currentColor.value),
            border = BorderStroke(3.dp, colorResource(id = R.color.transparent_grey)),
        ) {}
    }
}