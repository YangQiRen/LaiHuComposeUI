package tw.hulk.laihucomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import tw.hulk.laihucomposeui.ui.SearchBar
import tw.hulk.laihucomposeui.ui.theme.Beige3
import tw.hulk.laihucomposeui.ui.theme.LaiHuComposeUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaiHuComposeUITheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            backgroundColor = Color.White,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = "https://t3.ftcdn.net/jpg/02/84/87/88/360_F_284878821_E2nyf8wkMPLWnnn0D7DdgYAv7ngrCQTH.jpg"),
                                        contentDescription = "Logo",
                                        modifier = Modifier.fillMaxHeight(1f),
                                        contentScale = ContentScale.Crop,
                                    )
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                    ) {
                                        Text(
                                            buildAnnotatedString {
                                                withStyle(
                                                    SpanStyle(
                                                        color = Color.Black,
                                                        fontFamily = FontFamily.Serif,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 16.sp
                                                    )
                                                ) {
                                                    append("來")
                                                    withStyle(SpanStyle(Beige3)) {
                                                        append("虎")
                                                    }
                                                    append("直播")
                                                }
                                            }
                                        )
                                        Text(
                                            text = "laihu.live",
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                                SearchBar(
                                    modifier = Modifier
                                        .padding(
                                            vertical = 8.dp
                                        )
                                        .weight(1f)
                                )
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.padding(end = 12.dp).clickable {  }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_headphone),
                                        contentDescription = "Service",
                                        tint = Color(0xFFFF6400),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text(text = "客服", fontSize = 14.sp, color = Color(0xFFFF6400))
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
//                        NavigationGraph(navController = navController)
                    }
                }
            }
        }
    }
}


