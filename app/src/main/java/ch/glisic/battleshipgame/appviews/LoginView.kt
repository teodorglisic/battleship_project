package ch.glisic.battleshipgame.appviews

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.glisic.battleshipgame.R
import ch.glisic.battleshipgame.navigation.NavRoutes
import ch.glisic.battleshipgame.webservice.ApiRequester
import ch.glisic.battleshipgame.webservice.WebApiModelView


val model = WebApiModelView()
@Composable
fun LoginView(modifier: Modifier, nav: NavHostController) {
    model.getPingResult()
    var gameCode by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("")}

    val gameCodeLabel = stringResource(R.string.game_code)
    val userNameLabel = stringResource(R.string.user_name)

    Box() {

        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "Picture of a battleship",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.5f
        )

        // Help from Gemini when it came to centering the Items

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)) {


            Text(text = "${stringResource(R.string.app_name)} ${stringResource(R.string.login)}", fontWeight = FontWeight.Bold)

            Text(text = "Ping result is: ${model.getPing}")

            OutlinedTextField(
                value = gameCode,
                onValueChange = { gameCode = it } ,
                label = {
                    Text(text =  gameCodeLabel)
                }
            )

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it } ,
                label = {
                    Text(text = userNameLabel)
                }
            )

            Button(onClick = {
                Log.i("LoginView", "$gameCodeLabel: $gameCode")
                Log.i("LoginView", "$userNameLabel: $userName")
                nav.navigate(NavRoutes.PlaceShipsView.name)
            }, shape = RoundedCornerShape(20)) {
                Text(text = stringResource(R.string.submit))
            }
        }
    }

}