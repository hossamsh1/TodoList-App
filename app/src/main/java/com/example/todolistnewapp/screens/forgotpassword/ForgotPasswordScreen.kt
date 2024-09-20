package com.example.todolistapp.screens.forgotpassword


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistnewapp.R
import com.example.todolistnewapp.screens.login.TextFieldFunction
import com.example.todolistnewapp.screens.login.TextFieldFunctionPass


@Composable
fun ForgotPasswordScreen(navController: NavController) {

    var emailState = remember { mutableStateOf("") }
    var newpasswordState = remember { mutableStateOf("") }
    var confirmpasswordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {

        Image(
            modifier = Modifier
                .size(170.dp),
            painter = painterResource(id = R.drawable.splashtop),
            contentDescription = null
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 0.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Text(text = "Forgot Password?"
            , fontSize = 16.sp
            , fontWeight = FontWeight.Bold
            , color = colorResource(id = R.color.black)
            , modifier = Modifier.align(Alignment.CenterHorizontally))

            Text(text = "Don’t worry, we can help you get back \n on track with your account."
            , fontSize = 12.sp
                , color = Color.Gray
                , modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally)
                , textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(20.dp))


    TextFieldFunction(text1 = "Email Address"
        , lable = "Email"
        , text = emailState,
        keyboard = KeyboardOptions(keyboardType = KeyboardType.Email),
        icon =R.drawable.emailicon )

Spacer(modifier = Modifier.padding(12.dp))

    TextFieldFunctionPass(
        text1 = "Create New Password",
        label = "New Password",
        text = newpasswordState,
        keyboard = KeyboardOptions(keyboardType = KeyboardType.Password),
        iconVisible = R.drawable.visability,
        iconInvisible = R.drawable.invisability
    )

Spacer(modifier = Modifier.padding(12.dp))

    TextFieldFunctionPass(
        text1 = "Confirm New Password",
        label = "Confirm New Password",
        text = confirmpasswordState,
        keyboard = KeyboardOptions(keyboardType = KeyboardType.Password),
        iconVisible = R.drawable.visability,
        iconInvisible = R.drawable.invisability
    )


Spacer(modifier = Modifier.padding(30.dp))

    Button(onClick = {  }
        , colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary))
        , shape = RoundedCornerShape(10.dp)
        , modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 20.dp)
            .size(55.dp)) {
        Text(
            text = "Create New Password", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
        )
    }
        }
    }
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(navController = rememberNavController())
}