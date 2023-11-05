package com.example.dodomainlayout.network.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory") var idCategory : Int?,
    @SerializedName("strCategory") var strCategory : String?,
    @SerializedName("strCategoryDescription") var strCategoryDescription : String?,
    @SerializedName("strCategoryThumb") var strCategoryThumb : String?
)
