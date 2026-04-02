package ch.glisic.battleshipgame

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import ch.glisic.battleshipgame.navigation.Navigation
import ch.glisic.battleshipgame.ui.theme.BattleshipGameTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BattleshipGameTheme {
                val navController = rememberNavController()
                val mMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.faahhhh)
                Scaffold(modifier = Modifier.fillMaxSize()) { @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
                    Navigation(navController = navController,
                        modifier = Modifier.fillMaxSize(), player = mMediaPlayer)

                }
            }
        }
    }
}




