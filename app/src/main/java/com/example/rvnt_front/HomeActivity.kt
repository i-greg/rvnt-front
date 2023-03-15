package com.example.rvnt_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvnt_front.databinding.ActivityHomeBinding



class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var carouselHelper: CarouselHelper
    private lateinit var searchHelper: SearchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carouselHelper = CarouselHelper(viewPager2 = binding.viewPager2, binding = binding)

        carouselHelper.setupCarousel()

        //Dummy data for search functionality
        val cities = arrayOf(
            "New York City, USA", "Paris, France", "Tokyo, Japan",
            "London, UK", "Sydney, Australia", "Rio de Janeiro, Brazil",
            "Mumbai, India", "Beijing, China", "Moscow, Russia",
            "Dubai, UAE", "Rome, Italy", "Toronto, Canada",
            "Cape Town, South Africa", "Mexico City, Mexico",
            "Buenos Aires, Argentina", "Berlin, Germany", "Bangkok, Thailand",
            "Istanbul, Turkey", "Cairo, Egypt", "Seoul, South Korea"
        )

        val searchView = binding.searchView
        val suggestionListView = binding.lvSuggestions

        searchHelper = SearchHelper(
            searchView = searchView,
            suggestionListView = suggestionListView,
            suggestionData = cities,
            binding = binding
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop auto scrolling when the activity is destroyed
        carouselHelper.stopAutoScrolling()

    }
}

