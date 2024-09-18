package com.example.shoppinglist_toturialkotlin.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist_toturialkotlin.R
import com.example.shoppinglist_toturialkotlin.data.database.entities.ShoppingItem

class AddShoppingItemDialog(
    private val context: Context,
    private val listener: ItemShoppingListener
) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_item)

        this.findViewById<Button>(R.id.btnAdd)?.setOnClickListener {
            val name = this.findViewById<EditText>(R.id.edtName)?.text.toString()
            val amount = this.findViewById<EditText>(R.id.edtAmount)?.text.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please input", Toast.LENGTH_SHORT ).show()
                return@setOnClickListener
            }

            listener.onItemData(ShoppingItem(name, amount.toInt()))
            dismiss()
        }

        this.findViewById<Button>(R.id.btnCancel)?.setOnClickListener {
            cancel()
        }
    }
}