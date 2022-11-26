package xyz.hellosrinath.unitconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.hellosrinath.unitconverter.data.ConverterRepository
import javax.inject.Inject

class ConverterViewModelFactory @Inject constructor(private val repository: ConverterRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CoverterViewModel(repository) as T
}