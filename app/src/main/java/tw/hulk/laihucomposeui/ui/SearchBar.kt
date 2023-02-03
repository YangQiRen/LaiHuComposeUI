package tw.hulk.laihucomposeui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.hulk.laihucomposeui.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    var textFieldState by remember {
        mutableStateOf("")
    }

    BasicTextField(
        value = textFieldState,
        onValueChange = {
            textFieldState = it
        },
        modifier = modifier
            .background(Color(0xffefefef), CircleShape)
            .padding(8.dp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.fillMaxHeight(1f),
                    tint = Color(0xff717171)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textFieldState.isEmpty())
                        Text(
                            text = "搜索",
                            color = Color(0xFF999999),
                            fontSize = 14.sp,
                            style = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                        )
                    innerTextField()
                }
                if (textFieldState.isNotEmpty())
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Clear",
                        modifier = Modifier
                            .fillMaxHeight(1f)
                            .clickable { textFieldState = "" },
                        tint = Color.Black
                    )
            }
        }
    )
}