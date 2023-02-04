package tw.hulk.laihucomposeui.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import tw.hulk.laihucomposeui.R
import tw.hulk.laihucomposeui.ui.tabs.TabRowItem
import tw.hulk.laihucomposeui.ui.tabs.TabScreen
import tw.hulk.laihucomposeui.ui.theme.Orange

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val tabRowItems = listOf(
        TabRowItem(
            title = "Tab 1",
            screen = { TabScreen(text = "Tab 1") },
            iconId = R.drawable.ic_approval_24,
        ),
        TabRowItem(
            title = "Tab 2",
            screen = { TabScreen(text = "Tab 2") },
            iconId = R.drawable.ic_compost_24,
        ),
        TabRowItem(
            title = "Tab 3",
            screen = { TabScreen(text = "Tab 3") },
            iconId = R.drawable.ic_notifications_active_24,
        )
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Tabs(pagerState = pagerState, tabRowItems = tabRowItems)
        HorizontalPager(count = tabRowItems.size, state = pagerState) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState, tabRowItems: List<TabRowItem>) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Orange
            )
        },
    ) {
        tabRowItems.forEachIndexed { index, tabRowItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                text = {
                    Text(text = tabRowItem.title)
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    Tabs(pagerState = rememberPagerState(), tabRowItems = listOf(
        TabRowItem(
            title = "Tab 1",
            screen = { TabScreen(text = "Tab 1") },
            iconId = R.drawable.ic_approval_24,
        ),
        TabRowItem(
            title = "Tab 2",
            screen = { TabScreen(text = "Tab 2") },
            iconId = R.drawable.ic_compost_24,
        ),
        TabRowItem(
            title = "Tab 3",
            screen = { TabScreen(text = "Tab 3") },
            iconId = R.drawable.ic_notifications_active_24,
        )
    ))
}