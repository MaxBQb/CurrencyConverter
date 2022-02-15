package lab.maxb.currency_converter.dependencyInjection

import lab.maxb.currency_converter.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val MODULE_viewModels = module {
    viewModel { MainViewModel(get(), get()) }
}