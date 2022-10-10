package tw.kotlin.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tw.kotlin.core.ui.theme.StringResource
import tw.kotlin.core.ui.util.supportWideScreen

@Composable
fun SuccessScreen(
    byteArray: ByteArray,
    navToSignIn: () -> Unit
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = byteArray,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(256.dp)
            )

            Button(
                onClick = navToSignIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 28.dp, bottom = 3.dp)
            ) {
                Text(
                    text = StringResource.signIn,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}