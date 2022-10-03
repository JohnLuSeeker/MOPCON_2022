package tw.kotlin.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.kotlin.core.ui.R
import tw.kotlin.core.ui.theme.StringResource

@Composable
fun Branding(
    painter: Painter,
    text: String
) {
    Column(
        modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(painter = painter)
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun Logo(painter: Painter) {
    Image(
        painter = painter,
        modifier = Modifier
            .padding(horizontal = 76.dp)
            .clip(CircleShape),
        contentDescription = null
    )
}

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

@Preview
@Composable
fun LogoPreview1() {
    val painter = painterResource(id = R.drawable.ic_logo)
    Box(Modifier.fillMaxWidth()) {
        Image(
            painter = painter,
            modifier = Modifier
                // .padding(36.dp)
                // .clip(CircleShape)
                .size(256.dp),
            contentDescription = null
        )
    }
}
