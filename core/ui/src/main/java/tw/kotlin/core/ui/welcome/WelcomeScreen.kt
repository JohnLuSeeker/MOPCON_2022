//package tw.kotlin.core.ui.welcome
//
//sealed class WelcomeEvent {
//    data class SignInSignUp(val email: String) : WelcomeEvent()
//    object SignInAsGuest : WelcomeEvent()
//}
//
//@Composable
//fun WelcomeScreen(onEvent: (WelcomeEvent) -> Unit) {
//    var showBranding by remember { mutableStateOf(true) }
//
//    Surface(modifier = Modifier.supportWideScreen()) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .verticalScroll(rememberScrollState())
//        ) {
//            Spacer(
//                modifier = Modifier
//                    .weight(1f, fill = showBranding)
//                    .animateContentSize()
//            )
//
////            AnimatedVisibility(
////                showBranding,
////                Modifier.fillMaxWidth()
////            ) {
////                Branding()
////            }
//
//            Spacer(
//                modifier = Modifier
//                    .weight(1f, fill = showBranding)
//                    .animateContentSize()
//            )
//
//            SignInCreateAccount(
//                onEvent = onEvent,
//                onFocusChange = { focused -> showBranding = !focused },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//            )
//        }
//    }
//}
//
//@Composable
//private fun Branding(modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
//    ) {
//        Logo(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(horizontal = 76.dp)
//        )
//        Text(
//            text = stringResource(id = R.string.app_tagline),
//            style = MaterialTheme.typography.titleMedium,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .padding(top = 24.dp)
//                .fillMaxWidth()
//        )
//    }
//}
//
//@Composable
//private fun Logo(
//    modifier: Modifier = Modifier,
//    lightTheme: Boolean = LocalContentColor.current.luminance() < 0.5f,
//) {
//    val assetId = if (lightTheme) {
//        R.drawable.ic_logo_light
//    } else {
//        R.drawable.ic_logo_dark
//    }
//    Image(
//        painter = painterResource(id = assetId),
//        modifier = modifier,
//        contentDescription = null
//    )
//}
//
//@Composable
//private fun SignInCreateAccount(
//    onEvent: (WelcomeEvent) -> Unit,
//    onFocusChange: (Boolean) -> Unit,
//    modifier: Modifier = Modifier
//) {
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
//}
//
//@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun WelcomeScreenPreview() {
//    WelcomeScreen {}
//}