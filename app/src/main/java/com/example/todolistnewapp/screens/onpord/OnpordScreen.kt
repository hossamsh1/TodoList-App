package com.example.todolistapp.screens.onpord

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistnewapp.R

@Composable
fun OnBoardingScreen(navController: NavController) {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.background))){

        Image(modifier = Modifier
            .size(170.dp)
            .align(Alignment.TopStart)
            ,painter = painterResource(id = R.drawable.splashtop)
            , contentDescription =null
        )

        Column (modifier = Modifier.align(Alignment.Center)){

            Image(modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally)
                ,painter = painterResource(id = R.drawable.onpord)
                , contentDescription =null)

Spacer(modifier = Modifier.padding(8.dp))

            Text(modifier = Modifier.align(Alignment.CenterHorizontally)
                ,color = colorResource(id = R.color.black)
                , fontWeight = FontWeight.Bold
                ,fontSize = 16.sp
                ,text = "Get things done with TODOO")

            Spacer(modifier = Modifier.padding(4.dp))

            Text(modifier = Modifier.align(Alignment.CenterHorizontally)
                , textAlign = TextAlign.Center
                ,color = Color.DarkGray
                , fontWeight = FontWeight.Normal
                ,fontSize = 14.sp
                ,text ="Lorem ipsum dolor sit amet,\n" +
                        " consectetur adipiscing elit.Tempor \n" +
                        "duis sed duis suspendisse et.Non \n" +
                        " fames nibh non auctor malesuada ut\n" +
                        " consectetur.Ut quis id risus elit.")

            Button(onClick = { navController.navigate("loginScreen") }
                , colors =ButtonDefaults.buttonColors(colorResource(id = R.color.primary))
                , shape = RoundedCornerShape(10.dp)
            , modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 50.dp)
                    .size(55.dp)){
                Text(text = "Get Starting"
                , fontSize = 20.sp
                , fontWeight = FontWeight.Bold
                , color = Color.White
                )

            }
        }

    }
    }

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen(navController = rememberNavController())
}