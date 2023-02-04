package tw.hulk.laihucomposeui.model

data class Video(
    val imageUrl: String = "https://www.welbloom.com.tw/wp-content/uploads/2021/08/%E9%81%8B%E5%8B%95%E7%9B%B4%E6%92%AD-%E5%B0%81%E9%9D%A2%E5%9C%96.jpg",
    val avatarUrl: String = "https://images-tw.girlstyle.com/wp-content/uploads/2022/09/b5d32ce3.jpg?auto=format&w=1053",
    val avatarName: String = "這個女人叫小美",
    val views: Int = 3213,
)

enum class LayoutType {
    OneBigMulti, TwoTwo, Horizontal
}

sealed class LayoutItem {
    data class HeaderItem(
        val title: String,
        val actionText: String? = null,
    ) : LayoutItem()

    data class VideoItem(
        val videoList: List<Video>,
        val type: LayoutType = LayoutType.OneBigMulti,
    ) : LayoutItem()
}

fun getDummyData(): MutableList<LayoutItem> {
    val list = mutableListOf<LayoutItem>()

    list.add(LayoutItem.HeaderItem(title = "標題1", actionText = "更多"))
    list.add(
        LayoutItem.VideoItem(
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
    list.add(LayoutItem.HeaderItem(title = "標題2", actionText = "更多"))
    list.add(
        LayoutItem.VideoItem(
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
            type = LayoutType.Horizontal
        )
    )
    list.add(LayoutItem.HeaderItem(title = "標題3", actionText = "更多"))
    list.add(
        LayoutItem.VideoItem(
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
            type = LayoutType.TwoTwo
        )
    )
    return list
}