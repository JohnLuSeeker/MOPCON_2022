package tw.kotlin.mopcon2022.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import tw.kotlin.core.ui.components.Branding
import tw.kotlin.core.ui.util.supportWideScreen
import tw.kotlin.mopcon2022.R

@Composable
fun SuccessScreen() {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .animateContentSize()
            )

            Branding(
                painter = painterResource(id = R.drawable.ic_logo)
            )

            Spacer(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .animateContentSize()
            )
        }
    }
}