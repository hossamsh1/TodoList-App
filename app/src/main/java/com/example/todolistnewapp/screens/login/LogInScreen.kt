package com.example.todolistnewapp.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.model.LoginViewModel
import com.example.todolistnewapp.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = LoginViewModel()) {

    val coroutineScope = rememberCoroutineScope()

    // Initialize MutableState variables
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Collect email and password from DataStore and update MutableState
    val email by viewModel.emailFlow(context).collectAsState("")
    val password by viewModel.passwordFlow(context).collectAsState("")

    emailState.value = email.toString()
    passwordState.value = password.toString()

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
        ) {

            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(150.dp),
                painter = painterResource(id = R.drawable.splashscreen),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = "Login",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = colorResource(id = R.color.black),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(5.dp))

            // Pass MutableState<String> instead of String
            TextFieldFunction(
                text1 = "Email Address",
                lable = "Email",
                text = emailState, // Corrected to pass MutableState<String>
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Email),
                icon = R.drawable.emailicon
            )

            Spacer(modifier = Modifier.padding(8.dp))

            TextFieldFunctionPass(
                text1 = "Password",
                label = "Password",
                text = passwordState,
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Password),
                iconVisible = R.drawable.visability,
                iconInvisible = R.drawable.invisability
            )
            Spacer(modifier = Modifier.padding(3.dp))

            Text(text = "Forgot Password ?"
                , color = colorResource(id = R.color.primary)
                , fontSize = 16.sp
            , modifier = Modifier
                    .padding(end = 25.dp)
                    .align(Alignment.End)
                    .clickable { navController.navigate("forgotPasswordScreen") }
            )
Spacer(modifier = Modifier.padding(3.dp))
            Row (modifier = Modifier
                .padding(start = 15.dp)
                .align(Alignment.Start)){

                Checkbox(checked = checked, onCheckedChange = {checked = it
                    coroutineScope.launch {
                        if (checked) {
                            // Save email and password
                            viewModel.saveCredentials(context, emailState.value, passwordState.value)
                            Toast.makeText(context, "Email and Password are Saved to remember", Toast.LENGTH_SHORT).show()
                        } else {
                            // Clear email and password
                            viewModel.clearCredentials(context)
                            Toast.makeText(context, "Email and Password are not remembered", Toast.LENGTH_SHORT).show()
                        }
                    }})
                Text(text = "Remember Me"
                    , modifier = Modifier.align(Alignment.CenterVertically)
                    , color = Color.DarkGray
                    , fontSize = 16.sp
                )
            }


            Spacer(modifier = Modifier.padding(3.dp))

            Button(onClick = { navController.navigate("dashboardScreen") }
                , colors =ButtonDefaults.buttonColors(colorResource(id = R.color.primary))
                , shape = RoundedCornerShape(10.dp)
                , modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 20.dp)
                    .size(55.dp)) {
                Text(
                    text = "Sign In",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
Spacer(modifier = Modifier.padding(3.dp))

                Row (modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.CenterHorizontally)){
                    Text(text ="Don’t have an account?   "
                    , color = Color.DarkGray
                    , fontSize = 16.sp)
                    Text(text ="Sign Up"
                        , color = colorResource(id = R.color.primary)
                        , fontSize = 16.sp
                        , fontWeight = FontWeight.Bold
                        ,modifier = Modifier.clickable { navController.navigate("signUpScreen") })

                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldFunction(
    text1: String,
    lable: String,
    text: MutableState<String>, // Use MutableState<String>
    keyboard: KeyboardOptions,
    icon: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
    ) {
        Text(
            text = text1,
            modifier = Modifier,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )
Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            placeholder = {
                Text(
                    text = lable,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon), // Use ImageVector.vectorResource
                    contentDescription = null,
                    tint = Color.Black
                    ,modifier = Modifier
                        .align(Alignment.End)
                        .size(25.dp)
                )
            },
            keyboardOptions = keyboard,
            textStyle = TextStyle(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(8.dp)
            ,colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = colorResource(id = R.color.primary)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldFunctionPass(
    text1: String,
    label: String,
    text: MutableState<String>,
    keyboard: KeyboardOptions,
    isPassword: Boolean = true, // Default to true since it's for a password field
    iconVisible: Int,
    iconInvisible: Int
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
    ) {
        Text(
            text = text1,
            modifier = Modifier,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.padding(3.dp))
        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            placeholder = {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            },
            trailingIcon = {
                val image = if (passwordVisible) iconVisible else iconInvisible
                Icon(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            passwordVisible = !passwordVisible
                        }
                        .size(25.dp)
                )
            },
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboard,
            textStyle = TextStyle(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = colorResource(id = R.color.primary)
            )
        )
    }
}
