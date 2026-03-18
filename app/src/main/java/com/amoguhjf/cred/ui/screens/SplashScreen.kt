package com.amoguhjf.cred.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amoguhjf.cred.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val fullText = "Amorta"
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        fullText.forEachIndexed { index, _ ->
            delay(300)
            displayedText = fullText.substring(0, index + 1)
        }
        delay(500)
        navController.navigate(RootScreen.Main) {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }
    }


    Column(modifier = Modifier.fillMaxSize()
        .background(Brush.verticalGradient(listOf(
            colorResource(R.color.white), colorResource(R.color.green)))).systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Image(painter = painterResource(id = R.drawable.splashlogo),
            contentDescription = "Logo", modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = displayedText,
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold,
                color = Color(0xFF2D4CF8), letterSpacing = 4.sp,
                 fontFamily = FontFamily(Font(R.font.coinyregular))
            )
        )
    }
}