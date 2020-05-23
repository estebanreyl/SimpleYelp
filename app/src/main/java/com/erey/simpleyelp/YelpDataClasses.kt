package com.erey.simpleyelp

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val restaurants: List<YelpRestaurant>
)

class YelpRestaurant (
    val name: String,
    val rating: Double,
    val price: String,
    val categories: List<YelpCategory>,
    val location: YelpLocation,
    @SerializedName("review_count") val numReviews: Int,
    @SerializedName("distance") val distanceInMeters: Double,
    @SerializedName("image_url") val imageUrl: String
    ) {
    fun displayDistance(): String {
        val kmPerMeter = 0.001
        val distanceInKm = "%.2f".format(distanceInMeters * kmPerMeter)
        return "$distanceInKm km"
    }
}

class YelpCategory (
    val title: String
)

class YelpLocation (
    @SerializedName("address1") val address: String
)