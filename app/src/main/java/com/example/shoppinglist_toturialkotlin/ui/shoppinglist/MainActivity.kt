package com.example.shoppinglist_toturialkotlin.ui.shoppinglist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_toturialkotlin.R
import com.example.shoppinglist_toturialkotlin.data.database.ShoppingDatabase
import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem
import com.example.shoppinglist_toturialkotlin.data.repositories.ShoppingRepository
import com.example.shoppinglist_toturialkotlin.others.AddShoppingItemDialog
import com.example.shoppinglist_toturialkotlin.others.ItemShoppingListener
import com.example.shoppinglist_toturialkotlin.adapters.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var rvShoppingList: RecyclerView

    @Inject
    lateinit var database: ShoppingDatabase
    @Inject
    lateinit var repository: ShoppingRepository
    @Inject
    lateinit var factory: ShoppingViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapterShoppingList = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingList = findViewById(R.id.rvShoppingList)
        rvShoppingList.adapter = adapterShoppingList
        rvShoppingList.layoutManager = LinearLayoutManager(this)

        viewModel.getAll().observe(this) {
            adapterShoppingList.items = it
            adapterShoppingList.notifyDataSetChanged()
        }

        findViewById<FloatingActionButton>(R.id.floatingActionButton)?.setOnClickListener {
            val dialog = AddShoppingItemDialog(this,
                object: ItemShoppingListener {
                    override fun onItemData(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                })
            dialog.show()
        }
    }
}