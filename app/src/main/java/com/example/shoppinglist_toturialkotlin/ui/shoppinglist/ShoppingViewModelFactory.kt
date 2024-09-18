package com.example.shoppinglist_toturialkotlin.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist_toturialkotlin.data.repositories.ShoppingRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(shoppingRepository) as T
    }
}