package com.example.dodomainlayout.network.model

import com.google.gson.annotations.SerializedName

data class Pizza(
    @SerializedName("id") var id : Int?,
    @SerializedName("name") var name : String?,
    @SerializedName("ingredients") var ingredients : Array<String>?
)
