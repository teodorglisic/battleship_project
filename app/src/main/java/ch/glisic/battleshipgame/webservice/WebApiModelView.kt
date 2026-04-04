package ch.glisic.battleshipgame.webservice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WebApiModelView: ViewModel() {

    var getPing by mutableStateOf("")
        private set

    fun getPingResult() {
        viewModelScope.launch {
            getPing = ApiRequester.retrofitService.getPing()
        }
    }

}