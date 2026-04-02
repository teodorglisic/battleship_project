package ch.glisic.battleshipgame.navigation

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ch.glisic.battleshipgame.appviews.LoginView
import ch.glisic.battleshipgame.appviews.PlaceShipView

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier, player: MediaPlayer) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LoginView.name,
        modifier = modifier
    ) {
        composable(route = NavRoutes.LoginView.name) {
            LoginView(modifier = modifier, nav = navController)
        }

        composable(route = NavRoutes.PlaceShipsView.name) {
            PlaceShipView(player)
        }
    }
}