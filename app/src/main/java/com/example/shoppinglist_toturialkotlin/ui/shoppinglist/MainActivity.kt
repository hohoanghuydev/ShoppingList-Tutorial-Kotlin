package com.example.shoppinglist_toturialkotlin.ui.shoppinglist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_toturialkotlin.R
import com.example.shoppinglist_toturialkotlin.data.database.ShoppingDatabase
import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem
import com.example.shoppinglist_toturialkotlin.data.repositories.ShoppingRepository
import com.example.shoppinglist_toturialkotlin.ui.adapters.ShoppingItemAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var rvShoppingList: RecyclerView
    private var shoppingItems: List<ShoppingItem> = arrayListOf(
        ShoppingItem("Cafe", 2),
        ShoppingItem("Latte", 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapterShoppingList = ShoppingItemAdapter(shoppingItems, viewModel)

        rvShoppingList = findViewById(R.id.rvShoppingList)
        rvShoppingList.adapter = adapterShoppingList
        rvShoppingList.layoutManager = LinearLayoutManager(this)

        viewModel.getAll().observe(this, Observer {
            shoppingItems = it
            adapterShoppingList.notifyDataSetChanged()
        })
    }
}