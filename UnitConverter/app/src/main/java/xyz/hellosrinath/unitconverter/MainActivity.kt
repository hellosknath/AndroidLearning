package xyz.hellosrinath.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import xyz.hellosrinath.unitconverter.data.ConverterDatabase
import xyz.hellosrinath.unitconverter.data.ConverterRepositoryImpl
import xyz.hellosrinath.unitconverter.ui.theme.UnitConverterTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ConverterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BaseScreen(factory)
                }
            }
        }
    }
}
