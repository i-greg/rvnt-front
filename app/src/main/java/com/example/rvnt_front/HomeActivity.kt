package com.example.rvnt_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvnt_front.api.ApiManager
import com.example.rvnt_front.databinding.ActivityHomeBinding


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

    override fun onPause() {
        super.onPause()
        //Stop focus on searchView
        binding.searchView.clearFocus()
    }


    override fun onDestroy() {
        super.onDestroy()
        //Stop auto scrolling when the activity is destroyed
        carouselHelper.stopAutoScrolling()
        //Stop focus on searchView
        binding.searchView.clearFocus()

    }

    private fun setupCarousel(){
        carouselHelper = CarouselHelper(viewPager2 = binding.viewPager2, binding = binding)
        carouselHelper.setupCarousel()
        }

    private fun setupSearch(){
        val apiManager = ApiManager()
        apiManager.getSuggestionData { suggestionsData ->
            val citiesList = mutableListOf<SearchHelper.CityWithId>()
            for (data in suggestionsData) {
                citiesList.add(SearchHelper.CityWithId(data.city, data._id))
            }

            searchHelper = SearchHelper(
                searchView = binding.searchView,
                suggestionListView = binding.lvSuggestions,
                suggestionData = citiesList,
                binding = binding
            )
        }
    }

    private fun setupCard(){
        //CardView Functionality
        eventList = ArrayList()
        prepareEventListData()

            recyclerView = binding.rvCard
            cardViewAdapter = CardViewAdapter(this@HomeActivity, eventList)
            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
            recyclerView!!.layoutManager = layoutManager
            recyclerView!!.adapter = cardViewAdapter
    }

    private fun prepareEventListData() {
        val apiManager = ApiManager()
        apiManager.getCardData { cardData ->
            //Handle response here
            for (data in cardData) {
                val eventName = data.category
                val imageUrl = data.category_img
                val id = data._id
                val event = CardViewAdapter.Event(eventName, imageUrl, id)

                eventList.add(event)
            }
            cardViewAdapter!!.notifyDataSetChanged()
        }

    }

}



