package tw.kotlin.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import tw.kotlin.core.ui.components.Email
import tw.kotlin.core.ui.components.ErrorSnackbar
import tw.kotlin.core.ui.components.Password
import tw.kotlin.core.ui.components.TopBar
import tw.kotlin.core.ui.model.EmailState
import tw.kotlin.core.ui.model.EmailStateSaver
import tw.kotlin.core.ui.model.PasswordState
import tw.kotlin.core.ui.model.VerificationCodeState
import tw.kotlin.core.ui.theme.StringResource
import tw.kotlin.core.ui.util.supportWideScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignIn: (String, String, String) -> Unit,
    onBackPressed: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.supportWideScreen(),
        topBar = {
            TopBar(
                topAppBarText = StringResource.signIn,
                onBackPressed = onBackPressed
            )
        },
        content = { paddingValues ->
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SignInContent(
                        onSignIn = onSignIn
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackbar(
            snackbarHostState = snackbarHostState,
            onDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SignInContent(
    onSignIn: (String, String, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState())
        }
        val passwordState = remember { PasswordState() }
        val codeState = remember { VerificationCodeState() }
        val onSubmit = {
            if (emailState.isValid && passwordState.isValid && codeState.isValid) {
                onSignIn(emailState.text, passwordState.text, codeState.text)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Email(emailState = emailState, imeAction = ImeAction.Done, onImeAction = onSubmit)
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            label = StringResource.password,
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            label = StringResource.verificationCode,
            passwordState = codeState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSubmit() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = emailState.isValid && passwordState.isValid && codeState.isValid
        ) {
            Text(
                text = StringResource.signIn
            )
        }
    }
}