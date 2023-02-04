package tw.hulk.laihucomposeui.ui.tabs

import androidx.compose.runtime.Composable

data class TabRowItem(
    val title: String,
    val iconId: Int,
    val screen: @Composable () -> Unit,
)