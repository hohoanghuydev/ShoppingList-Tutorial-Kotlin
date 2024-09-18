package com.example.shoppinglist_toturialkotlin.data.repositories

import com.example.shoppinglist_toturialkotlin.data.database.ShoppingDatabase
import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem

class ShoppingRepository(
    private var database: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = database.getShoppingItemDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = database.getShoppingItemDao().deleteItem(item)

    fun getAll() = database.getShoppingItemDao().getAll()
}