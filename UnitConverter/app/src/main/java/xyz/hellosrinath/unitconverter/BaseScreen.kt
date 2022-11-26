package xyz.hellosrinath.unitconverter

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.hellosrinath.unitconverter.compose.history.HistoryScreen
import xyz.hellosrinath.unitconverter.compose.converter.TopScreen

@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: CoverterViewModel =
        viewModel(factory = factory)
) {
    val list = converterViewModel.getConversions()
    val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current
    var isLandScape by remember {
        mutableStateOf(false)
    }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandScape = true
            Row(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandScape
                ) { msg1, msg2 ->
                    converterViewModel.addResult(msg1, msg2)
                }
                Spacer(modifier = modifier.width(10.dp))
                HistoryScreen(historyList, { item ->
                    converterViewModel.removeResult(item)
                }, {
                    converterViewModel.clearAll()
                })
            }
        }
        else -> {
            isLandScape = false
            Column(modifier = modifier.padding(30.dp)) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandScape
                ) { msg1, msg2 ->
                    converterViewModel.addResult(msg1, msg2)
                }
                Spacer(modifier = modifier.height(20.dp))
                HistoryScreen(historyList, { item ->
                    converterViewModel.removeResult(item)
                }, {
                    converterViewModel.clearAll()
                })
            }
        }
    }

}