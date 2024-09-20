package com.example.todolistapp.screens.dashbourd

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.todolistnewapp.R
import com.example.todolistnewapp.composablefunctions.TaskList
import com.example.todolistnewapp.composablefunctions.showTimePickerDialog
import com.example.todolistnewapp.model.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBordNewUser(
    navController: NavController,
    viewModel: TaskViewModel=viewModel()
) {
    val context = LocalContext.current
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF4DD0E1),
                Color.White
            )
        )
    }
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }
    var sheetState1 = rememberModalBottomSheetState()
    var isSheetOpen1 by remember { mutableStateOf(false) }
    val tasks by viewModel.getAllTasks().collectAsState(initial = emptyList())

    var picUri by remember {
        mutableStateOf(getDefaultImageUri(context, R.drawable.nameicon))
    }
    val resultLancherImage = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        if (it != null) {
            picUri = it
        }
    }


    if (isSheetOpen1) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen1 = false },
            sheetState = sheetState1,
            dragHandle = { },
        ) {
            AddNewTask(
                onDismiss = { isSheetOpen1 = false }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .background(color = colorResource(id = R.color.primary))
            .height(280.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(170.dp),
                painter = painterResource(id = R.drawable.splashtop),
                contentDescription = null
            )

            Column(modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 70.dp)) {
                Text(
                    text = "Hi there, User \n Add a Profile Picture",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(10.dp))

                val borderWidth = 4.dp
//                tasks.forEach { task ->
//                    val taskImage=task.image?.let { Uri.parse(it) }
                Image(
                    painter = rememberAsyncImagePainter(model =picUri,
                        imageLoader =ImageLoader(context)),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clickable {
                            resultLancherImage.launch("image/*")
                            //  viewModel.upsertTasks(Task(task = "New Task"),picUri)
                        }
                        .align(Alignment.CenterHorizontally)
                        .size(120.dp)
                        .border(
                            BorderStroke(borderWidth, rainbowColorsBrush),
                            CircleShape
                        )
                        .padding(borderWidth)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
            //}
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
                ClockRepresentation(
                    hour = selectedHour,
                    minute = selectedMinute,
                    onClick = {
                        showTimePickerDialog(context) { hour, minute ->
                            selectedHour = hour
                            selectedMinute = minute
                            Toast.makeText(context, "Alarm set for $hour:$minute", Toast.LENGTH_SHORT)
                                .show()
                            // Optionally set the alarm here
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Text(
                text = "Set up your task lists for tomorrow",
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.padding(8.dp))

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 35.dp, end = 35.dp)
                    .height(180.dp),
                colors = CardDefaults.elevatedCardColors(Color.White),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(6.dp)
            ) {
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = "Daily Tasks",
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.padding(start = 150.dp))
                    Box(modifier = Modifier
                        .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.iconadd),
                            contentDescription = null,
                            tint = colorResource(id = R.color.primary),
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    isSheetOpen1 = true
                                    //  sheetState1.show()
                                }
                        )
                    }
                }

                LazyColumn(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                    items(tasks){ task ->
                        TaskList(task=task,
                            viewModel = viewModel,
                            taskData = task.task
                            , isCompletedData = task.isCompleted) }
                    }
                }

            Button(onClick = {  }
                , colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary))
                , shape = RoundedCornerShape(10.dp)
                , modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp, end = 30.dp)
                    .align(Alignment.End)
            ) {
                Text(
                    text = "Create", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
                )
            }
            }
        }
    }

@Preview(showBackground = true, device = "id:pixel_8")
@Composable
fun DashBordNewUserPreview() {
DashBordNewUser(navController = rememberNavController(), viewModel = viewModel())

}






@Composable
fun ClockRepresentation(
    hour: Int,
    minute: Int,
    onClick: () -> Unit
) {

    Surface(
        shape = CircleShape,
        shadowElevation = 8.dp,
        modifier = Modifier
            .size(150.dp)
            .padding(top = 0.dp)
            .clickable(onClick = onClick),
        color = Color(0xFFE0F7FA)
    ) {
        Canvas(
            modifier = Modifier
                .size(150.dp)
                .padding(0.dp)
        ) {
            val center = Offset(size.width / 2, size.height / 2)

            // Draw clock numbers
            drawContext.canvas.nativeCanvas.apply {
                val fontSize = 18.sp.toPx()
                val paint = android.graphics.Paint().apply {
                    textSize = fontSize
                    color = android.graphics.Color.parseColor("#00838F")
                    textAlign = android.graphics.Paint.Align.CENTER
                }
                drawText("12", center.x, center.y - 60.dp.toPx() + fontSize / 2, paint)
                drawText("3", center.x + 60.dp.toPx(), center.y + fontSize / 2, paint)
                drawText("6", center.x, center.y + 60.dp.toPx() + fontSize / 2, paint)
                drawText("9", center.x - 60.dp.toPx(), center.y + fontSize / 2, paint)
            }

            // Draw hour hand
            drawLine(
                color = Color(0xFF00838F),
                start = center,
                end = center.copy(
                    x = center.x + 25.dp.toPx() * Math.cos(Math.toRadians((hour % 12) * 30.0 - 90.0)).toFloat(),
                    y = center.y + 30.dp.toPx() * Math.sin(Math.toRadians((hour % 12) * 30.0 - 90.0)).toFloat()
                ),
                strokeWidth = 8.dp.toPx(),
                cap = StrokeCap.Round
            )

            // Draw minute hand
            drawLine(
                color = Color(0xFFB0BEC5),
                start = center,
                end = center.copy(
                    x = center.x + 35.dp.toPx() * Math.cos(Math.toRadians(minute * 6.0 - 90.0)).toFloat(),
                    y = center.y + 45.dp.toPx() * Math.sin(Math.toRadians(minute * 6.0 - 90.0)).toFloat()
                ),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )

            // Draw the center circle
            drawCircle(
                color = Color(0xFF99B6D6),
                radius = 8.dp.toPx()
            )
        }
    }
}


@Composable
fun MyImageComposable(context: Context) {
    // Get the default image Uri
    var picUri by remember {
        mutableStateOf<Uri?>(getDefaultImageUri(context, R.drawable.nameicon))
    }

    // Display the image using the Uri
    picUri?.let { uri ->
        Image(
            painter = rememberAsyncImagePainter(model = uri),
            contentDescription = null
        )
    }
}

// Function to create a Uri from a drawable resource
fun getDefaultImageUri(context: Context, resId: Int): Uri {
    return Uri.parse("android.resource://${context.packageName}/$resId")
}