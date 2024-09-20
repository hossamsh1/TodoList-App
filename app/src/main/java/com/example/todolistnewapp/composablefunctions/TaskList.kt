package com.example.todolistnewapp.composablefunctions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.screens.dashbourd.UpdateTask
import com.example.todolistnewapp.R
import com.example.todolistnewapp.data.Task
import com.example.todolistnewapp.model.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(task:Task
             , viewModel: TaskViewModel
             ,taskData:String
             ,isCompletedData:Boolean){
    val context = LocalContext.current
    //  var text by remember { mutableStateOf("") }
    var isCompleted by remember { mutableStateOf(isCompletedData) }
    var sheetState2 = rememberModalBottomSheetState()
    var isSheetOpen2 by remember { mutableStateOf(false) }



    if (isSheetOpen2) {

        ModalBottomSheet(
            onDismissRequest = { isSheetOpen2 = !isSheetOpen2 },
            sheetState = sheetState2,
            dragHandle = { },
        ) {
            UpdateTask(
                onDismiss = { isSheetOpen2 = !isSheetOpen2 }
                , task = task.task, id = task.id
            )
        }
    }


    Row(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .height(30.dp)
        .clickable { isSheetOpen2 = true }) {
        Checkbox(checked = isCompleted
            , onCheckedChange ={ isChecked ->
                isCompleted=isChecked
                viewModel.upsertTask(task=Task( task = task.task, isCompleted = isCompleted, id = task.id))
                               }
            , colors = CheckboxDefaults.colors(colorResource(id = R.color.primary))
            , modifier = Modifier.padding(start = 4.dp))

        Text(text = task.task,
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .padding(start = 5.dp)) }
}

