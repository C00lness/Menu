package com.example.dodomainlayout.network.model

import com.google.gson.annotations.SerializedName

data class CategoryArray(
    @SerializedName("categories") var categories : Array<Category>?
)
