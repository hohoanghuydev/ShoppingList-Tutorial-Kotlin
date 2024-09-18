package com.example.shoppinglist_toturialkotlin.others

import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem

interface ItemShoppingListener {
    fun onItemData(item: ShoppingItem)
}