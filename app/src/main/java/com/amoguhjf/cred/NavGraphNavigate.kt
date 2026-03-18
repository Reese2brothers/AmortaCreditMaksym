package com.amoguhjf.cred

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amoguhjf.cred.ui.screens.GrafikScreen
import com.amoguhjf.cred.ui.screens.MainScreen
import com.amoguhjf.cred.ui.screens.NotificationsScreen
import com.amoguhjf.cred.ui.screens.RootScreen
import com.amoguhjf.cred.ui.screens.SplashScreen

private const val ANIMATION_DURATION = 100

val fastEnterTransition = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(ANIMATION_DURATION)
) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
val fastExitTransition = slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(ANIMATION_DURATION)
) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
val fastPopEnterTransition = slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = tween(ANIMATION_DURATION)
) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
val fastPopExitTransition = slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tween(ANIMATION_DURATION)
) + fadeOut(animationSpec = tween(ANIMATION_DURATION))


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavGraphNavigate(navController: NavHostController) {
    NavHost(navController = navController, startDestination =  RootScreen.Splash,
        enterTransition = { fastEnterTransition },
        exitTransition = { fastExitTransition },
        popEnterTransition = { fastPopEnterTransition },
        popExitTransition = { fastPopExitTransition }
    ) {
        composable<RootScreen.Splash> { SplashScreen(navController = navController) }
        composable<RootScreen.Main> { MainScreen(navController = navController) }
        composable<RootScreen.Grafik> { GrafikScreen(navController = navController) }
        composable<RootScreen.Notifications> { NotificationsScreen(navController = navController) }
    }
}