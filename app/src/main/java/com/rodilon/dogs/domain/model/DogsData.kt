package com.rodilon.dogs.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DogsData(

    @SerializedName("category")
    @Expose
    val category: String,

    @SerializedName("list")
    @Expose
    val breedsUrlList: List<String>
)