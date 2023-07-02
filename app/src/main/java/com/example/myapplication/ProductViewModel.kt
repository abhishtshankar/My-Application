package com.example.myapplication

import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {

    private val apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

    // Function to fetch the list of products from the API
    fun getProducts() = apiService.getProducts()
}
