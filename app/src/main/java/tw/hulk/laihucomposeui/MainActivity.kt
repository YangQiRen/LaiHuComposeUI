package tw.hulk.laihucomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import tw.hulk.laihucomposeui.ui.SearchBar
import tw.hulk.laihucomposeui.ui.approval.ApprovalScreen
import tw.hulk.laihucomposeui.ui.bottomnav.BottomNavItem
import tw.hulk.laihucomposeui.ui.compost.CompostScreen
import tw.hulk.laihucomposeui.ui.home.HomeScreen
import tw.hulk.laihucomposeui.ui.network.NetworkScreen
import tw.hulk.laihucomposeui.ui.notification.NotificationScreen
import tw.hulk.laihucomposeui.ui.theme.Beige3
import tw.hulk.laihucomposeui.ui.theme.LaiHuComposeUITheme
import tw.hulk.laihucomposeui.ui.theme.Orange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaiHuComposeUITheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    backgroundColor = Color.White,
                    topBar = {
                        TopAppBar(
                            backgroundColor = Color.White,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.fillMaxHeight()
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
                                    modifier = Modifier
                                        .padding(end = 12.dp)
                                        .clickable { }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_headphone),
                                        contentDescription = "Service",
                                        tint = Orange,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text(text = "客服", fontSize = 14.sp, color = Orange)
                                }
                            }
                        }
                    },
                    bottomBar = {
                        BottomNavigation(navController = navController) {
                            // 測試顯示snackbar是否會被遮擋
//                            scope.launch {
//                                scaffoldState.snackbarHostState.showSnackbar(
//                                    "$it is clicked!",
//                                    actionLabel = "action",
//                                    SnackbarDuration.Short
//                                )
//                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavigationGraph(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController, onNavClick: (String) -> Unit) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork,
        BottomNavItem.Compost,
        BottomNavItem.Notification,
        BottomNavItem.Approval
    )
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp,
                        color = if (currentRoute == item.screen_route) Orange else Color.Black
                    )
                },
                selectedContentColor = Orange,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    onNavClick(item.screen_route)
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.MyNetwork.screen_route) {
            NetworkScreen()
        }
        composable(BottomNavItem.Compost.screen_route) {
            CompostScreen()
        }
        composable(BottomNavItem.Notification.screen_route) {
            NotificationScreen()
        }
        composable(BottomNavItem.Approval.screen_route) {
            ApprovalScreen()
        }
    }
}

