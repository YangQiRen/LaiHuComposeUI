package tw.hulk.laihucomposeui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.hulk.laihucomposeui.R
import tw.hulk.laihucomposeui.ui.theme.Orange
import tw.hulk.laihucomposeui.ui.theme.Typography

@Composable
fun TitleBar(
    modifier: Modifier = Modifier,
    titleText: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 5.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(Orange)
        )

        Text(
            text = titleText,
            style = Typography.h1,
            modifier = Modifier
                .weight(1f),
            fontSize = 20.sp
        )
        if (!actionText.isNullOrEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onActionClick?.invoke() }) {
                Text(text = actionText, style = Typography.body1, color = Orange)
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = "Forward",
                    tint = Orange,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleBarPreview() {
    TitleBar(
        titleText = "即時賽程",
        actionText = "更多",
        onActionClick = {

        })
}