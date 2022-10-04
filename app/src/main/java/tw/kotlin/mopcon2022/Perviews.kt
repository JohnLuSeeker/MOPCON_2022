package tw.kotlin.mopcon2022

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.kotlin.core.ui.R
import tw.kotlin.core.ui.components.Logo
import tw.kotlin.core.ui.theme.StringResource

//TODO 1
@Preview
@Composable
fun BrandingPreview1() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = null
    )
}

@Preview
@Composable
fun BrandingPreview2() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = null
    )
    Text(
        text = StringResource.MOPCON
    )
}

@Preview
@Composable
fun BrandingPreview3() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )
        Text(
            text = StringResource.MOPCON
        )
    }
}

@Preview
@Composable
fun BrandingPreview4() {
    Column(
        modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )
        Text(
            text = StringResource.MOPCON,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
}

// TODO 2: Modifiers
@Preview
@Composable
fun LogoPreview1() {
    Box(Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            modifier = Modifier
                // .padding(36.dp)
                // .clip(CircleShape)
                .size(160.dp),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun BrandingPreview() {
    Column(
        modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(painter = painterResource(id = R.drawable.ic_logo))
        Text(
            text = StringResource.MOPCON,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun TextFieldPreview() {
    OutlinedTextField(
        value = "",
        onValueChange = {
        }
    )
}

@Preview
@Composable
fun TextFieldPreview2() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        }
    )
}