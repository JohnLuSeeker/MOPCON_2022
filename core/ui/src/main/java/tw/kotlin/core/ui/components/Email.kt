package tw.kotlin.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.ImeAction
import tw.kotlin.core.ui.model.EmailState
import tw.kotlin.core.ui.model.TextFieldState

@Composable
fun Email(
    emailState: TextFieldState = remember { EmailState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
//    OutlinedTextField(
//        value = emailState.text,
//        onValueChange = {
//            emailState.text = it
//        },
//        label = {
//            Text(
//                text = stringResource(id = R.string.email),
//                style = MaterialTheme.typography.bodyMedium,
//            )
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .onFocusChanged { focusState ->
//                emailState.onFocusChange(focusState.isFocused)
//                if (!focusState.isFocused) {
//                    emailState.enableShowErrors()
//                }
//            },
//        textStyle = MaterialTheme.typography.bodyMedium,
//        isError = emailState.showErrors(),
//        keyboardOptions = KeyboardOptions.Default.copy(
//            imeAction = imeAction,
//            keyboardType = KeyboardType.Email
//        ),
//        keyboardActions = KeyboardActions(
//            onDone = {
//                onImeAction()
//            }
//        ),
//    )
//
//    emailState.getError()?.let { error -> TextFieldError(textError = error) }
}