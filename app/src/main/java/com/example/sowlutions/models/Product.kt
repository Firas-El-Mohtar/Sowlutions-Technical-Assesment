package com.example.sowlutions.models

data class Product(
    val brand_name: String,
    val brand_type: String,
    val color: String,
    val description: String,
    val discounted_price: String,
    val favorite: Boolean,
    val featured: Boolean,
    val group_description: Any,
    val group_quantity: Any,
    val height: Any,
    val id: Int,
    val images: List<Image>,
    val klass: String,
    val price: String,
    val requires_freezing: Boolean,
    val requires_refrigeration: Boolean,
    val serving_size: String,
    val serving_unit: String,
    val sort_order: Int,
    val status: String,
    val title: String,
    val weight: Any,
    val width: Any
)