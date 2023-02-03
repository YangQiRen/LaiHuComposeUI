package tw.hulk.laihucomposeui.ui.bottomnav

import tw.hulk.laihucomposeui.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem("Home", R.drawable.ic_home_24,"home")
    object MyNetwork: BottomNavItem("My Network",R.drawable.ic_network_check_24,"my_network")
    object Compost: BottomNavItem("Compost",R.drawable.ic_compost_24,"compost")
    object Notification: BottomNavItem("Notification",R.drawable.ic_notifications_active_24,"notification")
    object Approval: BottomNavItem("Approval",R.drawable.ic_approval_24,"approval")
}