package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        val productTypeTextView: TextView = itemView.findViewById(R.id.productTypeTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val taxTextView: TextView = itemView.findViewById(R.id.taxTextView)
        val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        Log.d("ProductAdapter", "Product Name: ${product.productName}")

        holder.productNameTextView.text = product.productName
        holder.productTypeTextView.text = product.productType
        holder.priceTextView.text = product.price.toString()
        holder.taxTextView.text = product.tax.toString()
        Glide.with(holder.itemView.context)
            .load(product.image)
            .placeholder(R.drawable.image) // Use a default image if URL is empty
            .into(holder.productImageView)
    }
    override fun getItemCount(): Int {
        return products.size
    }
    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
