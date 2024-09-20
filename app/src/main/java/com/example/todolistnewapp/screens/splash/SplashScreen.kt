package com.example.todolistapp.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.navigation.AppRouts
import com.example.todolistnewapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController:NavController) {

    // Navigate to the home screen after a delay
    LaunchedEffect(Unit) {
        delay(2000) // 3 seconds delay
        navController.navigate(AppRouts.ONPORDER_SCREEN)
    }

   Box (modifier = Modifier
       .fillMaxSize()
       .background(color = colorResource(id = R.color.background))){

       Image(modifier = Modifier
           .size(161.dp)
           .align(Alignment.Center)
           ,painter = painterResource(id = R.drawable.splashscreen)
           , contentDescription =null)

       Image(modifier = Modifier
           .size(170.dp)
           .align(Alignment.TopStart)
           ,painter = painterResource(id = R.drawable.splashtop)
           , contentDescription =null
       )

Box(modifier = Modifier
    .wrapContentSize()
    .padding(top = 100.dp)
    .align(Alignment.TopEnd)) {
    Image(modifier = Modifier
        .size(300.dp)
        .padding(start = 150.dp)
        .align(Alignment.TopEnd)
        ,painter = painterResource(id = R.drawable.spalshdecor1)
        , contentDescription =null
    )
}


Box(
   modifier = Modifier
       .padding(top = 550.dp)
) {
   Image(
       modifier = Modifier
           .padding(start = 220.dp)
           .size(400.dp),
       painter = painterResource(id = R.drawable.splashdecor2),
       contentDescription = null
   )
}

Box(
   modifier = Modifier
       .align(Alignment.BottomStart)
) {
   Image(
       modifier = Modifier
           .padding(end = 200.dp, bottom = 100.dp)
           .size(300.dp),
       painter = painterResource(id = R.drawable.splashdecor3),
       contentDescription = null
   )
}


   }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}


