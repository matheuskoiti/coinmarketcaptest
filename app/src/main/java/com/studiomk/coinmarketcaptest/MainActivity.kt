package com.studiomk.coinmarketcaptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studiomk.navigation.NavigationItem
import com.studiomk.coinmarketcaptest.ui.theme.CoinMarketCapTestTheme
import com.studiomk.data.di.dataModule
import com.studiomk.domain.di.domainModule
import com.studiomk.exchangedetail.di.exchangeDetailModule
import com.studiomk.exchangedetail.ui.ExchangeDetailScreen
import com.studiomk.exchangelist.di.exchangeListModule
import com.studiomk.exchangelist.ui.ExchangeListScreen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()
        enableEdgeToEdge()
        setContent {
            CoinMarketCapTestTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Coin Market Cap - Exchange Info") }) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = rememberNavController()
                    )
                }
            }
        }
    }

    @Composable
    fun AppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        startDestination: String = NavigationItem.ExchangeList.route,
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationItem.ExchangeList.route) {
                ExchangeListScreen(
                    navController = navController
                )
            }
            composable(NavigationItem.ExchangeDetail.route) { backStackEntry ->
                val exchangeId = backStackEntry.arguments?.getString("id")
                ExchangeDetailScreen(navController = navController, exchangeId = exchangeId ?: "")
            }
        }
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(dataModule)
            modules(domainModule)
            modules(exchangeListModule)
            modules(exchangeDetailModule)
        }
    }
}