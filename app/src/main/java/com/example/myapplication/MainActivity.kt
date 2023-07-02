package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.widget.SearchView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productListRecyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var productAdapter: ProductAdapter
    private var productList: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        productListRecyclerView = findViewById(R.id.productListRecyclerView)
        productListRecyclerView.layoutManager = LinearLayoutManager(this)

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterProducts(newText)
                return true
            }
        })
        productAdapter = ProductAdapter(productList)
        productListRecyclerView.adapter = productAdapter
        // Fetch products from API and update the RecyclerView
        productViewModel.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    productList = response.body() ?: emptyList()
                    productAdapter.updateProducts(productList)
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                // Handle API call failure
            }
        })
        val addProductButton = findViewById<Button>(R.id.addProductButton)
        addProductButton.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }
    }
    private fun filterProducts(query: String) {
        val filteredProducts = productList.filter { product ->
            product.productName?.contains(query, true) == true ||
                    product.productType?.contains(query, true) == true
        }
        productAdapter.updateProducts(filteredProducts)
    }
}

