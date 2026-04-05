package ch.glisic.battleshipgame.webservice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WebApiModelView: ViewModel() {

    var getPing by mutableStateOf(false)
        private set

    var getPost: Boolean? by mutableStateOf(null)
        private set

    var getPostString by mutableStateOf("")
        private set

    fun getPingResult() {
        viewModelScope.launch {
            getPing = ApiRequester.retrofitService.getPing().ping
        }
    }

    fun sendPost(body: StartGameContainer) {

        viewModelScope.launch {
            getPost = ApiRequester.retrofitService.joinGame(body).gameover
        }
    }

    fun sendPostString(body: String) {
        viewModelScope.launch {
            getPostString = ApiRequester.retrofitService.joinGameString(body)
        }
    }

}