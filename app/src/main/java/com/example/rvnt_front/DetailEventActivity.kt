package com.example.rvnt_front

/* A UI for event's detail*/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvnt_front.databinding.ActivityDetailEventBinding
import com.bumptech.glide.Glide
import com.example.rvnt_front.results.ResultsItem

class DetailEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 31/3/2023
        // Receive the selected ResultsItem from the previous activity
        //val selectedResult = intent.getParcelableExtra("selected_result") as ResultsItem?

        @Suppress("DEPRECATION")
        val selectedResult = intent.getParcelableExtra<ResultsItem>("selected_result") as ResultsItem


        // 31/3/2023
        // Set the title of the activity
        supportActionBar?.title = selectedResult?.title

        // 31/3/2023
        // Bind the data to the views
        binding.apply {


            eventTitle.text = selectedResult?.title
            eventDate.text = selectedResult?.date
            eventLocation.text = selectedResult?.city
            eventCategory.text = selectedResult?.category
            //Glide.with(this@DetailEventActivity).load(selectedResult?.imageUrl).into(ivEvent)
        }
    }
}


