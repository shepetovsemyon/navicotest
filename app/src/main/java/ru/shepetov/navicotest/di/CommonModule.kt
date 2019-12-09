package ru.shepetov.navicotest.di

import android.content.Context
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.shepetov.navicotest.ui.AttractionViewModel
import ru.shepetov.navicotest.ui.SharedViewModel

val commonModule = module {
    viewModel { AttractionViewModel(get()) }
    viewModel { SharedViewModel() }
}
