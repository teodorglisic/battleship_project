package ch.glisic.battleshipgame.appviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.glisic.battleshipgame.R
import ch.glisic.battleshipgame.navigation.NavRoutes
import ch.glisic.battleshipgame.webservice.WebApiModelView

@Composable
fun LoadingView(apiModel: WebApiModelView, nav: NavHostController) {

    println(apiModel.getPost)

    LaunchedEffect(apiModel.getPost) {
         if (apiModel.getPost != null) {
            nav.navigate(NavRoutes.GameBoardView.name)
        }
    }

    if (apiModel.getPost == null) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(R.string.loading_message))
            CircularProgressIndicator()
        }
    }






}