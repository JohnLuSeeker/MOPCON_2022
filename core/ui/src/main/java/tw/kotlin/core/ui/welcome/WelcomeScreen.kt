package tw.kotlin.core.ui.welcome

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.kotlin.core.ui.R
import tw.kotlin.core.ui.util.supportWideScreen
import tw.kotlin.core.ui.welcome.components.Branding

sealed class WelcomeEvent {
    data class SignInSignUp(val email: String) : WelcomeEvent()
    object SignInAsGuest : WelcomeEvent()
}

@Composable
fun WelcomeScreen(onEvent: (WelcomeEvent) -> Unit) {
    var showBranding by remember { mutableStateOf(true) }

    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f, fill = showBranding)
                    .animateContentSize()
            )

            AnimatedVisibility(
                showBranding,
                Modifier.fillMaxWidth()
            ) {
                Branding(
                    painter = painterResource(id = R.drawable.ic_logo),
                    tagLine = stringResource(id = R.string.tagline)
                )
            }

            Spacer(
                modifier = Modifier
                    .weight(1f, fill = showBranding)
                    .animateContentSize()
            )

            SignInCreateAccount(
                onEvent = onEvent,
                onFocusChange = { focused -> showBranding = !focused },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
private fun SignInCreateAccount(
    onEvent: (WelcomeEvent) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
//    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
//        mutableStateOf(EmailState())
//    }
//    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(
//            text = stringResource(id = R.string.sign_in_create_account),
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
//        )
//        val onSubmit = {
//            if (emailState.isValid) {
//                onEvent(WelcomeEvent.SignInSignUp(emailState.text))
//            } else {
//                emailState.enableShowErrors()
//            }
//        }
//        onFocusChange(emailState.isFocused)
//        Email(emailState = emailState, imeAction = ImeAction.Done, onImeAction = onSubmit)
//        Button(
//            onClick = onSubmit,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 28.dp, bottom = 3.dp)
//        ) {
//            Text(
//                text = stringResource(id = R.string.user_continue),
//                style = MaterialTheme.typography.titleSmall
//            )
//        }
//        OrSignInAsGuest(
//            onSignedInAsGuest = { onEvent(WelcomeEvent.SignInAsGuest) },
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
}

@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen {}
}