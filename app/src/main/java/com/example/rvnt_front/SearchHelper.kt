package com.example.rvnt_front

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.*
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

        suggestionListView.onItemClickListener = AdapterView.OnItemClickListener {

                adapterView, view, position, id ->

            val selectedItem = adapterView.getItemAtPosition(position) as String
            val itemposition = adapterView.getItemIdAtPosition(position)


            //to display a message once the item in list is clicked
            Toast.makeText(
                searchView.context,
                "You have clicked $selectedItem at position $itemposition",
                Toast.LENGTH_LONG
            ).show()


            //to open a new activity when item in a list is clicked
            val intent = Intent(suggestionListView.context, TestActivity::class.java)
            intent.putExtra("key", selectedItem)
            suggestionListView.context.startActivity(intent)

        }

    }

}