package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("product_type")
    val productType: String?,
    val image: String?,
    val price: Double,
    val tax: Double
)
