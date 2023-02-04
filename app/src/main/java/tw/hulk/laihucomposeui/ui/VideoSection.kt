package tw.hulk.laihucomposeui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import tw.hulk.laihucomposeui.model.LayoutItem
import tw.hulk.laihucomposeui.model.LayoutType
import tw.hulk.laihucomposeui.model.Video
import tw.hulk.laihucomposeui.ui.theme.Orange
import tw.hulk.laihucomposeui.ui.theme.Typography

@Composable
fun VideoSection(
    modifier: Modifier = Modifier,
    videoItem: LayoutItem.VideoItem,
) {
//    val horizontalState = rememberLazyListState()

    when (videoItem.type) {
        LayoutType.Horizontal -> {
            println("VideoSection Horizontal")
            val horizontalState = rememberScrollState()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .horizontalScroll(horizontalState),
            ) {
                videoItem.videoList.forEachIndexed { index, video ->
                    VideoCard(
                        video = video,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                            .width(LocalConfiguration.current.screenWidthDp.dp * 0.7f)
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
        LayoutType.TwoTwo -> {
            println("VideoSection TwoTwo")
            val fourItems = videoItem.videoList.take(4)
            if (fourItems.isNotEmpty()) {
                TwoVideoOneRow(videoList = fourItems)
            } else {
                Text(text = "Empty TwoTwo")
            }
        }
        LayoutType.OneBigMulti -> {
            println("VideoSection OneBigMulti")
            val firstVideo = videoItem.videoList.firstOrNull()
            val lastList = videoItem.videoList.drop(1)
            if (firstVideo != null) {
                VideoCard(video = firstVideo, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                if (lastList.isNotEmpty()) {
                    TwoVideoOneRow(videoList = lastList)
                }
            } else {
                Text(text = "Empty TwoTwo")
            }
        }
    }
}

@Composable
fun TwoVideoOneRow(
    videoList: List<Video>,
    modifier: Modifier = Modifier
) {
    for (i in videoList.indices step 2) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            VideoCard(
                video = videoList[i],
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .weight(1f)
            )
            if (i + 1 in videoList.indices) {
                VideoCard(
                    video = videoList[i + 1],
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .weight(1f)
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun VideoCard(
    modifier: Modifier = Modifier,
    video: Video
) {
    Column(
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(model = video.imageUrl),
                contentDescription = "Cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.size(1.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = video.avatarUrl),
                contentDescription = "avatarUrl",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = video.avatarName, style = Typography.body1, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = video.views.toString(),
                style = Typography.body1,
                fontSize = 14.sp,
                color = Orange
            )
        }
    }
}

@Preview
@Composable
fun VideoCardPreview() {
//    VideoCard(video = Video())

    VideoSection(
        videoItem = LayoutItem.VideoItem(
            videoList = listOf(
                Video(),
                Video(),
                Video(),
                Video(),
                Video(),
                Video(),
                Video(),
                Video(),
                Video(),
                Video(),
                Video(),
                Video()
            ),
            type = LayoutType.OneBigMulti
        )
    )
}