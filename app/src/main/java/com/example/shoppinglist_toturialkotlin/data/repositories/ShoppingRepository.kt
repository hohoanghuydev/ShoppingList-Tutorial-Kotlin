package com.example.shoppinglist_toturialkotlin.data.repositories

import com.example.shoppinglist_toturialkotlin.data.database.ShoppingDatabase
import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val database: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = database.getShoppingItemDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = database.getShoppingItemDao().deleteItem(item)

    fun getAll() = database.getShoppingItemDao().getAll()
}