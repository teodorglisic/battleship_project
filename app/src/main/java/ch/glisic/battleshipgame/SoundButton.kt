package ch.glisic.battleshipgame

import android.media.MediaPlayer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun SoundButton(modifier: Modifier) {
    val mMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.faahhhh)
    Button(modifier = modifier,
        onClick = { mMediaPlayer.start() }) { Text(text = "Play Sound") }


    DisposableEffect(Unit) {
        onDispose {
            mMediaPlayer.release()
        }
    }
}