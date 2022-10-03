package tw.kotlin.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import tw.kotlin.core.ui.components.Email
import tw.kotlin.core.ui.components.Password
import tw.kotlin.core.ui.components.TopBar
import tw.kotlin.core.ui.model.ConfirmPasswordState
import tw.kotlin.core.ui.model.EmailState
import tw.kotlin.core.ui.model.PasswordState
import tw.kotlin.core.ui.theme.StringResource
import tw.kotlin.core.ui.theme.stronglyDeemphasizedAlpha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSignUp: (String, String) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                topAppBarText = StringResource.createAccount,
                onBackPressed = onBackPressed
            )
        },
        content = { contentPadding ->
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    SignUpContent(
                        onSignUp = onSignUp
                    )
                }
            }
        }
    )
}

@Composable
fun SignUpContent(
    onSignUp: (String, String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
        val emailState = remember { EmailState() }
        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))
        val passwordState = remember { PasswordState() }
        Password(
            label = StringResource.password,
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
            modifier = Modifier.focusRequester(passwordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }
        Password(
            label = StringResource.confirmPassword,
            passwordState = confirmPasswordState,
            onImeAction = { onSignUp(emailState.text, passwordState.text) },
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = StringResource.termsAndConditions,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSignUp(emailState.text, passwordState.text) },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                    passwordState.isValid && confirmPasswordState.isValid
        ) {
            Text(text = StringResource.createAccount)
        }
    }
}