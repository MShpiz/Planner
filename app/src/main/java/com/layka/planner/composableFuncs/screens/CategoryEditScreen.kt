package com.layka.planner.composableFuncs.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.layka.planner.composableFuncs.ColorPickerPanel
import com.layka.planner.R
import com.layka.planner.ViewModels.EditCategoryViewModel
import com.layka.planner.data.TaskCategory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryEditScreen(navController: NavController, id: Long? = null, viewModel: EditCategoryViewModel = hiltViewModel()) {
    val context = LocalContext.current // контекст для тоста

    val showToast = fun (text: String) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    val gotData = remember {
        mutableStateOf(false)
    }
    val categoryName = remember {
        mutableStateOf("")
    }

    val tagColor = remember {
        mutableStateOf(Color.Red)
    }

    val catBackgroundColor = remember {
        mutableStateOf(Color.White)
    }

    if (!gotData.value) {
        viewModel.getCategory(id,
            fun (cat: TaskCategory){
                categoryName.value = cat.categoryName
                tagColor.value = cat.tagColor
                gotData.value = true
        })
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
        topBar = { TopAppBar(title = { Text(text = if (id != null) "Edit Category" else "Create Category")}) },
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
                TextField(value = categoryName.value, onValueChange = { categoryName.value = it },  Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .fillMaxWidth())

                ColorField("tag color: ", 1, tagColor, openColorPicker)

                Row( Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()) {
                    Button(onClick = {
                        val res = viewModel.save(TaskCategory(id, categoryName.value, tagColor.value))
                        if (res)
                            navController.popBackStack()
                        else
                            showToast("category name is blank")
                    },
                        Modifier.padding(10.dp).weight(1f)) {
                        Text(text = "Save")
                    }
                    if (id != null) {


                        Button(onClick = {
                            viewModel.delete(id)
                            navController.popBackStack()
                            },Modifier.padding(10.dp).weight(1f)) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
            // color picker "pop up"
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
    Row( Modifier
        .padding(horizontal = 10.dp)
        .padding(bottom = 10.dp)
        .fillMaxWidth()) {
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