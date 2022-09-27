package tw.kotlin.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tw.kotlin.core.ui.components.TopBar
import tw.kotlin.core.ui.model.EmailState
import tw.kotlin.core.ui.model.EmailStateSaver
import tw.kotlin.core.ui.util.supportWideScreen

sealed class SignInEvent {
    data class SignIn(val email: String, val password: String, val code: String) : SignInEvent()
    object SignUp : SignInEvent()
    object SignInAsGuest : SignInEvent()
    object NavigateBack : SignInEvent()
}

@OptIn(ExperimentalMaterial3Api::class) // Scaffold is experimental in m3
@Composable
fun SignIn(
    byteArray: ByteArray = byteArrayOf(),
    onNavigationEvent: (SignInEvent) -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.supportWideScreen(),
        topBar = {
            TopBar(
                topAppBarText = stringResource(id = R.string.sign_in),
                onBackPressed = { onNavigationEvent(SignInEvent.NavigateBack) }
            )
        },
        content = {
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SignInContent(
                        byteArray = byteArray,
                        onSignInSubmitted = { email, password, code ->
                            onNavigationEvent(SignInEvent.SignIn(email, password, code))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )

//    Box(modifier = Modifier.fillMaxSize()) {
//        ErrorSnackbar(
//            snackbarHostState = snackbarHostState,
//            onDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
//            modifier = Modifier.align(Alignment.BottomCenter)
//        )
//    }
}

@Composable
fun SignInContent(
    byteArray: ByteArray = byteArrayOf(),
    onSignInSubmitted: (email: String, password: String, code: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState())
        }
//        AsyncImage(
//            model = byteArray,
//            contentDescription = null,
//            modifier = Modifier.size(128.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Email(emailState, onImeAction = { focusRequester.requestFocus() })
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        val passwordState = remember { PasswordState() }
//        val codeState = remember { VerificationCodeState() }
//
//        val onSubmit = {
//            if (emailState.isValid && passwordState.isValid) {
//                onSignInSubmitted(emailState.text, passwordState.text, codeState.text)
//            }
//        }
//        Password(
//            label = stringResource(id = R.string.password),
//            passwordState = passwordState,
//            modifier = Modifier.focusRequester(focusRequester),
//            onImeAction = { onSubmit() }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Password(
//            label = stringResource(id = R.string.verification_code),
//            passwordState = codeState,
//            modifier = Modifier.focusRequester(focusRequester),
//            onImeAction = { onSubmit() }
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = { onSubmit() },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp),
//            enabled = emailState.isValid && passwordState.isValid
//        ) {
//            Text(
//                text = stringResource(id = R.string.sign_in)
//            )
//        }
    }
}