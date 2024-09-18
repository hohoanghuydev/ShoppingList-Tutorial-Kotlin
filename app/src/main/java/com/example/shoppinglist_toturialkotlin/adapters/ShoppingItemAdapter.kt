package com.example.shoppinglist_toturialkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist_toturialkotlin.R
import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem
import com.example.shoppinglist_toturialkotlin.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : Adapter<ShoppingItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.let { items.size }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val shoppingItem = items[position]

        val tvItemName = holder.itemView.findViewById<TextView>(R.id.tvItemName)
        tvItemName.text = shoppingItem.name

        val tvItemAmount = holder.itemView.findViewById<TextView>(R.id.tvItemAmount)
        tvItemAmount.text = shoppingItem.amount.toString()

        val btnPlus = holder.itemView.findViewById<ImageButton>(R.id.btnPlus)
        val btnMinus = holder.itemView.findViewById<ImageButton>(R.id.btnMinus)
        val btnDelete = holder.itemView.findViewById<ImageButton>(R.id.btnDelete)

        btnPlus.setOnClickListener {
            shoppingItem.amount++
            viewModel.upsert(shoppingItem)
        }

        btnMinus.setOnClickListener {
            if (shoppingItem.amount > 1) {
                shoppingItem.amount--
                viewModel.upsert(shoppingItem)
            }
        }

        btnDelete.setOnClickListener {
            viewModel.delete(shoppingItem)
        }
    }
}