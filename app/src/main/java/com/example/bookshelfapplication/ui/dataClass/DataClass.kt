package com.example.bookshelfapplication.ui.dataClass

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CountryData (

    @SerializedName("status"      ) var status      : String? = null,
    @SerializedName("status-code" ) var statusCode : Int?    = null,
    @SerializedName("version"     ) var version     : String? = null,
    @SerializedName("access"      ) var access      : String? = null,
    @SerializedName("data"        ) var data        : JsonObject?   = null

)
data class Country (

    @SerializedName("country" ) var country : String? = null,
    @SerializedName("region"  ) var region  : String? = null

)
data class Book (

    @SerializedName("id"              ) var id              : String? = null,
    @SerializedName("image"           ) var image           : String? = null,
    @SerializedName("hits"            ) var hits            : Int?    = null,
    @SerializedName("alias"           ) var alias           : String? = null,
    @SerializedName("title"           ) var title           : String? = null,
    @SerializedName("lastChapterDate" ) var lastChapterDate : Int?    = null,

)