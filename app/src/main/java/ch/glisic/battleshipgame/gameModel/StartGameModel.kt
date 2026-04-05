package ch.glisic.battleshipgame.gameModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ch.glisic.battleshipgame.webservice.ShipPosition

class StartGameModel: ViewModel() {

    var gamekey by mutableStateOf("")
    var username by mutableStateOf("")

    var position = mutableStateListOf<ShipPosition>()

}