package com.example.rvnt_front

import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import com.example.rvnt_front.databinding.ActivityHomeBinding

class SearchHelper(private val searchView: SearchView, private val suggestionListView: ListView,
                   private val suggestionData: Array<String>, private val binding: ActivityHomeBinding) {


    init {

        //Adapter for the suggestion list
        val suggestionAdapter : ArrayAdapter<String> = ArrayAdapter(
            searchView.context, android.R.layout.simple_list_item_1, suggestionData
        )

        suggestionListView.adapter = suggestionAdapter

        //Setting search and suggestion functionality
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()

                if (suggestionData.contains(query)){

                    suggestionAdapter.filter.filter(query)

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                suggestionAdapter.filter.filter(newText)

                if(TextUtils.isEmpty(newText)){
                    binding.lvSuggestions.visibility = View.GONE
                } else {
                    binding.lvSuggestions.visibility = View.VISIBLE
                }

                return false
            }

        })

    }

}