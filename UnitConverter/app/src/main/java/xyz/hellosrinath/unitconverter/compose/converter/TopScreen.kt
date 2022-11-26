package xyz.hellosrinath.unitconverter.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import xyz.hellosrinath.unitconverter.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    isLandScape: Boolean,
    save: (String, String) -> Unit
) {

    var toSave by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ConversionMenu(list = list, isLandScape) {
            selectedConversion.value = it
            typedValue.value = "0.0"

        }

        selectedConversion.value?.let {
            InputBlock(
                conversion = it, inputText = inputText,
                isLandScape
            ) { input ->
                typedValue.value = input
                toSave = true
            }
        }

        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value!!.multiplyBy
            val result = input * multiply;

            // rounding of the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val msg1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is " +
                    "equal to"
            val msg2 = "$roundedResult ${selectedConversion.value!!.convertTo}"
            if (toSave) {
                save(msg1, msg2)
                toSave = false
            }

            ResultBlock(msg1 = msg1, msg2 = msg2)
        }
    }

}