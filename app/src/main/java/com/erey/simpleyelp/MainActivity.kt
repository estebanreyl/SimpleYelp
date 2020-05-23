package com.erey.simpleyelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.yelp.com/v3/"
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit =
                Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY", "Avocado Toast", "New York").enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i(TAG, "$t")
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i(TAG, "$response")

            }
        })

    }
}
