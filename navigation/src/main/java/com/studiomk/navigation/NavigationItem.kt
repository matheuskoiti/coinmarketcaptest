package com.studiomk.navigation

enum class Screen {
    EXCHANGE_LIST,
    EXCHANGE_DETAIL,
}
sealed class NavigationItem(val route: String) {
    data object ExchangeList : NavigationItem(Screen.EXCHANGE_LIST.name)
    data object ExchangeDetail : NavigationItem("${Screen.EXCHANGE_DETAIL.name}/{id}")
}