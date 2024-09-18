package com.example.shoppinglist_toturialkotlin.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem
import com.example.shoppinglist_toturialkotlin.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private var shoppingRepository: ShoppingRepository
) : ViewModel() {
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        shoppingRepository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        shoppingRepository.delete(item)
    }

    fun getAll() = shoppingRepository.getAll()
}