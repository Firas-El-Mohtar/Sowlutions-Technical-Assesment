package com.example.sowlutions.models

data class Image(
    val file_name: String,
    val file_size: Int,
    val file_type: String,
    val id: Int,
    val large: String,
    val original: String,
    val thumbnail: String
)