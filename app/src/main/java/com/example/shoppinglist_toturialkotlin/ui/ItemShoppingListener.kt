package com.example.shoppinglist_toturialkotlin.ui

import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem

interface ItemShoppingListener {
    fun onItemData(item: ShoppingItem)
}