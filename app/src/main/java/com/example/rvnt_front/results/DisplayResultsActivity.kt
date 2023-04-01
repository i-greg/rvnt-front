package com.example.rvnt_front.results

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.HttpException
import com.example.rvnt_front.DetailEventActivity
import java.io.IOException
import com.example.rvnt_front.ResultsAdapter
import com.example.rvnt_front.databinding.ActivityDisplayResultsBinding

const val TAG = "DisplayResultsActivity"

class DisplayResultsActivity : AppCompatActivity(), ResultsAdapter.OnItemClickListener {

    private lateinit var binding: ActivityDisplayResultsBinding

    // creating an instance of our adapter
    private lateinit var resultAdapter: ResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initializing the binding
        binding = ActivityDisplayResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()


        // 31/3/2023
        // setting the click listener for the adapter
        resultAdapter.setOnItemClickListener(this)

        // We want to execute our api request on a background thread
        // so we launch a coroutine here

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getResults()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                resultAdapter.results = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }


    }



    // initialize the recyclerview
    private fun setupRecyclerView() = binding.rvResults.apply {
        // we set the adapter to a new adapter
        resultAdapter = ResultsAdapter()
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
        intent.putExtra("selected_result", result)
        startActivity(intent)
    }



}