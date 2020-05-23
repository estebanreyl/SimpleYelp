package com.erey.simpleyelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
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

        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter= RestaurantsAdapter(this, restaurants)
        rvRestaurants.adapter = adapter
        rvRestaurants.layoutManager = LinearLayoutManager(this)

        val retrofit =
                Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val yelpService :YelpService = retrofit.create(YelpService::class.java)

        button.setOnClickListener {
            val query : String = searchBar.text.toString()
            yelpService.searchRestaurants("Bearer $API_KEY", query, "New York").enqueue(object : Callback<YelpSearchResult> {

                override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                    Log.i(TAG, "$t")
                }

                override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                    Log.i(TAG, "$response")
                    val body = response.body()
                    if (body == null) {
                        Log.w(TAG, "Did not receive valid response from Yelp API... exiting")
                        return
                    }
                    restaurants.addAll(body.restaurants)
                    adapter.notifyDataSetChanged()
                }
            })
        }
    }

    fun doYelpSearch(query: String, yelpService : YelpService, adapter : RestaurantsAdapter){

    }
}


