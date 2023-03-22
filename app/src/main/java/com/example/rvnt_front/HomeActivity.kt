package com.example.rvnt_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvnt_front.databinding.ActivityHomeBinding
import org.json.JSONObject


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var carouselHelper: CarouselHelper
    private lateinit var searchHelper: SearchHelper
    private var recyclerView: RecyclerView? = null
    private var cardViewAdapter: CardViewAdapter? = null
    private var eventList = mutableListOf<CardViewAdapter.Event>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCarousel()
        setupSearch()
        setupCard()

    }


    override fun onDestroy() {
        super.onDestroy()
        // Stop auto scrolling when the activity is destroyed
        carouselHelper.stopAutoScrolling()

    }

    private fun setupCarousel(){
        carouselHelper = CarouselHelper(viewPager2 = binding.viewPager2, binding = binding)
        carouselHelper.setupCarousel()
    }

    private fun setupSearch(){

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

        searchHelper = SearchHelper(
            searchView = binding.searchView,
            suggestionListView = binding.lvSuggestions,
            suggestionData = cities,
            binding = binding
        )
    }

    private fun setupCard(){
        //CardView Functionality
        eventList = ArrayList()

        prepareEventListData()

        recyclerView = binding.rvCard
        cardViewAdapter = CardViewAdapter(this@HomeActivity, eventList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = cardViewAdapter


    }

    private fun prepareEventListData() {

        val jsonFileString = binding.root.context.assets.open("dummy_db.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonFileString)
        val eventsArray = jsonObject.getJSONArray("Events")

        for (item in 0 until eventsArray.length()) {
            val eventObject = eventsArray.getJSONObject(item)
            val eventName = eventObject.getString("title")
            val imageUrl = eventObject.getString("img")
            val event = CardViewAdapter.Event(eventName, imageUrl)

            eventList.add(event)
        }

        //cardViewAdapter!!.notifyDataSetChanged()

    }
}



