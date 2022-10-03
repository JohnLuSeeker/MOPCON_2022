package tw.kotlin.mopcon2022.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.kotlin.core.ui.components.Branding
import tw.kotlin.core.ui.components.Email
import tw.kotlin.core.ui.components.OrSignUp
import tw.kotlin.core.ui.model.EmailState
import tw.kotlin.core.ui.model.EmailStateSaver
import tw.kotlin.core.ui.theme.StringResource
import tw.kotlin.core.ui.theme.stronglyDeemphasizedAlpha
import tw.kotlin.core.ui.util.supportWideScreen
import tw.kotlin.mopcon2022.R

@Composable
fun WelcomeScreen() {
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
                    painter = painterResource(id = R.drawable.ic_logo)
                )
            }

            Spacer(
                modifier = Modifier
                    .weight(1f, fill = showBranding)
                    .animateContentSize()
            )

            SignInCreateAccount(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onFocusChange = { focused -> showBranding = !focused },
                onSignIn = {},
                onSignUp = {}
            )
        }
    }
}

@Composable
private fun SignInCreateAccount(
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit,
    onSignIn: (String) -> Unit,
    onSignUp: () -> Unit
) {
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState())
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = StringResource.signInCreateAccount,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )
        val onSubmit = {
            if (emailState.isValid) {
                onSignIn(emailState.text)
            } else {
                emailState.enableShowErrors()
            }
        }
        onFocusChange(emailState.isFocused)
        Email(emailState = emailState, imeAction = ImeAction.Done, onImeAction = onSubmit)
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 3.dp)
        ) {
            Text(
                text = StringResource.signIn,
                style = MaterialTheme.typography.titleSmall
            )
        }
        OrSignUp(
            onSignUp = onSignUp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}