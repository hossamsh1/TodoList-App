package com.example.todolistapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.screens.dashbourd.DashBordNewUser
import com.example.todolistapp.screens.forgotpassword.ForgotPasswordScreen
import com.example.todolistapp.screens.onpord.OnBoardingScreen
import com.example.todolistapp.screens.signup.SignUpScreen
import com.example.todolistapp.screens.splash.SplashScreen
import com.example.todolistnewapp.data.Task
import com.example.todolistnewapp.screens.login.LoginScreen

object AppRouts{
    const val SPLASH_SCREEN="splashScreen"
    const val ONPORDER_SCREEN="onporderScreen"
    const val LOGIN_SCREEN="loginScreen"
    const val SIGNUP_SCREEN="signUpScreen"
    const val FORGOTPASSWORD_SCREEN="forgotPasswordScreen"
    const val DASHBOARD_SCREEN="dashboardScreen"

}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRouts.SPLASH_SCREEN) {
        composable(route = AppRouts.SPLASH_SCREEN) { SplashScreen(navController) }
        composable(route = AppRouts.ONPORDER_SCREEN) { OnBoardingScreen(navController) }
        composable(route = AppRouts.LOGIN_SCREEN) { LoginScreen(navController) }
        composable(route = AppRouts.SIGNUP_SCREEN) { SignUpScreen(navController) }
        composable(route = AppRouts.FORGOTPASSWORD_SCREEN) { ForgotPasswordScreen(navController) }
        composable(route = AppRouts.DASHBOARD_SCREEN) { DashBordNewUser(navController)}

        }
    }

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}