package xyz.hellosrinath.unitconverter.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import xyz.hellosrinath.unitconverter.data.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn {
        items(
            items = list.value,
            key = { item -> item.id }
        ) { item ->
            HistoryItem(msgPart1 = item.msgPart1,
                msgPart2 = item.msgPart2,
                onClose = { onCloseTask(item) })
        }
    }
}