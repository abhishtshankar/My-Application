package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    private lateinit var productNameEditText: EditText
    private lateinit var sellingPriceEditText: EditText
    private lateinit var taxRateEditText: EditText
    private lateinit var productTypeSpinner: Spinner
    private val productList: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        productNameEditText = findViewById(R.id.productNameEditText)
        sellingPriceEditText = findViewById(R.id.sellingPriceEditText)
        taxRateEditText = findViewById(R.id.taxRateEditText)
        productTypeSpinner = findViewById(R.id.productTypeSpinner)

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val productName = productNameEditText.text.toString()
            val sellingPrice = sellingPriceEditText.text.toString()
            val taxRate = taxRateEditText.text.toString()
            val productType = productTypeSpinner.selectedItem.toString()
            if (productName.isBlank() || sellingPrice.isBlank() || taxRate.isBlank()) {
                if (productName.isBlank()) {
                    productNameEditText.error = "Product name cannot be blank"
                }
                if (sellingPrice.isBlank()) {
                    sellingPriceEditText.error = "Selling price cannot be blank"
                }
                if (taxRate.isBlank()) {
                    taxRateEditText.error = "Tax rate cannot be blank"
                }
                return@setOnClickListener
            }
            val product = Product(
                productName,
                productType,
                "",
                sellingPrice.toDouble(),
                taxRate.toDouble()
            )
            productList.add(product)
            finish()
        }

    }
}
