package ch.glisic.battleshipgame.navigation

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ch.glisic.battleshipgame.apiModel
import ch.glisic.battleshipgame.appviews.GameBoardView
import ch.glisic.battleshipgame.appviews.LoadingView
import ch.glisic.battleshipgame.appviews.LoginView
import ch.glisic.battleshipgame.appviews.PlaceShipView
import ch.glisic.battleshipgame.gameModel.StartGameModel

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    player: MediaPlayer,
    startGameModel: StartGameModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LoginView.name,
        modifier = modifier
    ) {
        composable(route = NavRoutes.LoginView.name) {
            LoginView(modifier = modifier, nav = navController, startGameModel)
        }

        composable(route = NavRoutes.PlaceShipsView.name) {
            PlaceShipView(player, startGameModel, nav = navController)
        }

        composable (route = NavRoutes.LoadingView.name) {
            LoadingView(apiModel, nav = navController)
        }

        composable(route = NavRoutes.GameBoardView.name) {
            GameBoardView(startGameModel)
        }
    }
}