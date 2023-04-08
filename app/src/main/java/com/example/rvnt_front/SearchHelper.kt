package com.example.rvnt_front

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.rvnt_front.databinding.ActivityHomeBinding
import com.example.rvnt_front.results.DisplayResultsActivity
import com.example.rvnt_front.results.Results

class SearchHelper(private val searchView: SearchView,
                   private val suggestionListView: ListView,
                   private val suggestionData: List<CityWithId>,
                   private val binding: ActivityHomeBinding) {


    init {

        val suggestionDataNames = suggestionData.map { it.city }

        //Adapter for the suggestion list
        val suggestionAdapter : ArrayAdapter<String> = ArrayAdapter(
            searchView.context, android.R.layout.simple_list_item_1, suggestionDataNames
        )

        suggestionListView.adapter = suggestionAdapter

        //Setting search and suggestion functionality
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()

                //Get the 1st suggestion
                val selectedItem = suggestionData.firstOrNull { it.city.equals(query.toString(), ignoreCase = true) }
                    ?: suggestionData.firstOrNull { it.city.startsWith(query.toString(), ignoreCase = true) }

                //Clear text from searchview
                searchView.setQuery("", false)

                if (selectedItem != null){

                    //To open a new activity when item in a list is clicked
                    val intent = Intent(suggestionListView.context, DisplayResultsActivity::class.java)
                    intent.putExtra("city_id", selectedItem.id)
                    suggestionListView.context.startActivity(intent)

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

        suggestionListView.onItemClickListener = AdapterView.OnItemClickListener {

                adapterView, view, position, id ->

            val selectedItem = suggestionData[position]

            //to open a new activity when item in a list is clicked
            val intent = Intent(suggestionListView.context, DisplayResultsActivity::class.java)
            intent.putExtra("city_id", selectedItem.id)
            suggestionListView.context.startActivity(intent)

        }

    }
    data class CityWithId(
        val city: String,
        val id: String
    )

}