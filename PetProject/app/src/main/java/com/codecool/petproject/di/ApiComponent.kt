package com.codecool.petproject.di

import com.codecool.petproject.main.MainViewModel
import com.codecool.petproject.model.CharactersService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CharactersService)

    fun inject(viewModel: MainViewModel)

}