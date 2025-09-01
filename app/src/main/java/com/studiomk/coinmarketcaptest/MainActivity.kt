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
import androidx.compose.ui.Modifier
import com.studiomk.coinmarketcaptest.ui.theme.CoinMarketCapTestTheme
import com.studiomk.data.di.dataModule
import com.studiomk.domain.di.domainModule
import com.studiomk.exchangedetail.di.exchangeDetailModule
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
                    topBar = { TopAppBar(title = { Text("Exchange List") }) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ExchangeListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
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