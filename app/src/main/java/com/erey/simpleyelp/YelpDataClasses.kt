package com.erey.simpleyelp

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val restaurants: List<YelpRestaurant>
)

class YelpRestaurant (
    @SerializedName("name") val name: String
)
