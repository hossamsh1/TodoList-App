package com.example.todolistapp.screens.dashbourd


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.screens.onpord.ui.theme.ToDoListAppTheme
import com.example.todolistnewapp.R
import com.example.todolistnewapp.data.Task
import com.example.todolistnewapp.model.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTask(onDismiss: () -> Unit,
               id: Int,
               task: String,
               viewModel: TaskViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {


    var data by remember { mutableStateOf(task) }
    var context = LocalContext.current

    Column(modifier = Modifier.background(Color.White)) {
            OutlinedTextField(
                value = data,
                onValueChange = {
                    data=it
                },
                placeholder = {
                    Text(
                        text = "Update Task",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.updatetask),
                        contentDescription = null,
                        tint = colorResource(id = R.color.primary),
                        modifier = Modifier
                            .align(Alignment.End)
                            .size(30.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 25.dp, end = 25.dp, top = 20.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = colorResource(id = R.color.primary)
                )
            )

        Spacer(modifier = Modifier.padding(3.dp))

        Row(modifier = Modifier
            .background(Color.White)
            .padding(top = 8.dp)
            .align(Alignment.CenterHorizontally)) {
            Button(onClick = { viewModel.upsertTask(Task(id,data,))
                Toast.makeText(context, "task is Updated", Toast.LENGTH_SHORT).show()
                             onDismiss()},
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Update",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { viewModel.deleteTask(Task(id, data))
                Toast.makeText(context, "task is deleted", Toast.LENGTH_SHORT).show()
                             onDismiss()},
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Delete",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        }


    }



@Preview(showBackground = true)
@Composable
fun UpdateTaskPreview() {
    ToDoListAppTheme {
        UpdateTask(onDismiss = {}, task = Task( 0,"Sample Task").toString(), id = 1)
    }
}






