package com.example.rvnt_front.results

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvnt_front.DetailEventActivity
import com.example.rvnt_front.ResultsAdapterTwo
import com.example.rvnt_front.api.ApiManager
import com.example.rvnt_front.databinding.ActivityDisplayResultsBinding
import com.example.rvnt_front.databinding.ActivityHomeBinding
import com.example.rvnt_front.models.ResultsItem
import com.example.rvnt_front.results.DetailEventItem as DetailEventItem

const val TAG = "DisplayResultsActivity"

class DisplayResultsActivity : AppCompatActivity(), ResultsAdapterTwo.OnItemClickListener {

    private lateinit var binding: ActivityDisplayResultsBinding

    // creating an instance of our adapter
    private lateinit var resultAdapter: ResultsAdapterTwo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        val back = binding.backButton

        back.setOnClickListener {

            this.onBackPressed()
        }
//



        val extras = intent.extras

        if (extras != null && extras.containsKey("category_id")) {
            val  value = extras.getString("category_id")
            val categoryName = extras.getString("criteria")
            val apiManager = ApiManager()
            apiManager.getCategoryResultsItem(value!!) { eventData ->
                if (eventData.isNotEmpty()) {
                    binding.tvResultsCount.text = "Results for $categoryName: ${eventData.size}"
                }else{
                    binding.tvResultsCount.text = "No results found"
                }
                setupRecyclerView(eventData)

                // 31/3/2023
                // setting the click listener for the adapter
               resultAdapter.setOnItemClickListener(this)


            }
        } else {
            val  value2 = extras?.getString("city_id")
            val cityName = extras?.getString("criteria")
            val apiManager = ApiManager()
            apiManager.getCityResultsItem(value2!!) { eventData ->
                if (eventData.isNotEmpty()) {
                    binding.tvResultsCount.text = "Results in $cityName: ${eventData.size}"
                }else{
                    binding.tvResultsCount.text = "No results found"
                }
                setupRecyclerView(eventData)
                // 31/3/2023
                // setting the click listener for the adapter
                resultAdapter.setOnItemClickListener(this)
            }
        }

    }
            // initialize the recyclerview
            private fun setupRecyclerView(detailEventList: List<com.example.rvnt_front.models.ResultsItem>) = binding.rvResults.apply {
                // we set the adapter to a new adapter
                resultAdapter = ResultsAdapterTwo(this@DisplayResultsActivity, detailEventList)
                // then we set the adapter of our recyclerview to this adapter
                adapter = resultAdapter
                // then we set the layout manager of this recyclerview to a linear layout
                // manager and pass "this" at main activity

                layoutManager = LinearLayoutManager(this@DisplayResultsActivity)
                Log.d(TAG, "RecyclerView setup finished")
            }


    // 31/3/2023
            // handle item click event
   override fun onItemClick(result: ResultsItem) {
                // do something when an item is clicked
                // e.g., start a new activity with the details of the selected item
                // or show a dialog box with more information about the selected item
                // Start the DetailEventActivity with the selected ResultsItem as an extra

                val intent = Intent(this@DisplayResultsActivity, DetailEventActivity::class.java)
                intent.putExtra("event_id", result._id)
                startActivity(intent)

            }

}