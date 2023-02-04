package tw.hulk.laihucomposeui.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import tw.hulk.laihucomposeui.R
import tw.hulk.laihucomposeui.model.LayoutItem
import tw.hulk.laihucomposeui.model.LayoutType
import tw.hulk.laihucomposeui.model.Video
import tw.hulk.laihucomposeui.model.getDummyData
import tw.hulk.laihucomposeui.ui.TitleBar
import tw.hulk.laihucomposeui.ui.VideoSection
import tw.hulk.laihucomposeui.ui.tabs.TabRowItem
import tw.hulk.laihucomposeui.ui.tabs.TabScreen
import tw.hulk.laihucomposeui.ui.theme.Orange
import tw.hulk.laihucomposeui.ui.theme.Typography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val tabRowItems = listOf(
        TabRowItem(
            title = "關注",
            screen = { FollowScreen() },
            iconId = R.drawable.ic_approval_24,
        ),
        TabRowItem(
            title = "推薦",
            screen = { TabScreen(text = "Tab 2") },
            iconId = R.drawable.ic_compost_24,
        ),
        TabRowItem(
            title = "資訊",
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

@Composable
fun FollowScreen() {
    val state = rememberScrollState()
    val dataList = getDummyData()

    Column(
//        state = state,
        modifier = Modifier.fillMaxSize().verticalScroll(state = state)
    ) {
        dataList.forEachIndexed { index, layoutItem ->
            when (layoutItem) {
                is LayoutItem.HeaderItem -> {
                    TitleBar(headerItem = layoutItem)
                }
                is LayoutItem.VideoItem -> {
                    VideoSection(videoItem = layoutItem)
                }
            }
        }
//        items(dataList) { layoutItem ->
//            when (layoutItem) {
//                is LayoutItem.HeaderItem -> {
//                    TitleBar(headerItem = layoutItem)
//                }
//                is LayoutItem.VideoItem -> {
//                    VideoSection(videoItem = layoutItem)
//                }
//            }
//        }
    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        VideoSection(
//            videoItem = LayoutItem.VideoItem(
//                videoList = listOf(
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video(),
//                    Video()
//                ),
//                type = LayoutType.Horizontal
//            )
//        )
//    }
}