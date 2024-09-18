package com.example.shoppinglist_toturialkotlin.di

import android.content.Context
import com.example.shoppinglist_toturialkotlin.data.database.ShoppingDatabase
import com.example.shoppinglist_toturialkotlin.data.repositories.ShoppingRepository
import com.example.shoppinglist_toturialkotlin.ui.shoppinglist.ShoppingViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProvideDependencyService {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = ShoppingDatabase(context)

    @Provides
    fun provideRepository(database: ShoppingDatabase) = ShoppingRepository(database)

    @Provides
    fun provideFactory(repository: ShoppingRepository) = ShoppingViewModelFactory(repository)
}