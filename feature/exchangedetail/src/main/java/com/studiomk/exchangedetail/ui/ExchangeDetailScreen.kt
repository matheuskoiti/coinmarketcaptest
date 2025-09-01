package com.studiomk.exchangedetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.exchangedetail.viewmodel.ExchangeDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ExchangeDetailViewModel = koinViewModel(),
    exchangeId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(exchangeId) {
        viewModel.loadDetails(exchangeId)
    }

    when(uiState){
        is ExchangeDetailState.ExchangeDetailLoadedState -> {
            ExchangeDetailInfo(modifier, (uiState as ExchangeDetailState.ExchangeDetailLoadedState).exchange)
        }
        is ExchangeDetailState.ExchangeLoadError -> {
            ExchangeError(
                errorMessage = "Error! ",
                onTryAgainClick = { viewModel.loadDetails(exchangeId) }
            )
        }
        ExchangeDetailState.ExchangeLoading -> {
            LoadingScreen()
        }
    }
}

@Composable
private fun ExchangeDetailInfo(modifier: Modifier, exchange: ExchangeUi) {
    Surface(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "ID: ${exchange.id}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = "Logo",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = exchange.name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Description: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Urls: www.teste.com",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Taker fee",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Maker fee",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Date launched: 12/12/1990",
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ExchangeError(
    errorMessage: String,
    onTryAgainClick: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "Erro!"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onTryAgainClick.invoke() }
            ) {
                Text(
                    text = "Try again",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ExchangeDetailScreenPreview() {
    ExchangeDetailScreen(exchangeId = "12222", navController = rememberNavController())
}