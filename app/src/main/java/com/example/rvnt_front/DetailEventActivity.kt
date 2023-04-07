package com.example.rvnt_front

/* A UI for event's detail*/
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rvnt_front.databinding.ActivityDetailEventBinding
import com.bumptech.glide.Glide
import com.example.rvnt_front.api.ApiManager
import com.example.rvnt_front.models.DetailEventItem
import com.example.rvnt_front.results.DisplayResultsActivity
import com.example.rvnt_front.results.ResultsItem

class DetailEventActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailEventBinding
    private var ticketsTotal = 0
    private var ticketsRemaining = 0
    private var eventPrice = 0
    private var eventDate = ""
    private var eventTime = ""
    private var eventLocation = ""





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // Taking eventID from HomeActivity
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("event_id")
            val apiManager = ApiManager()
            apiManager.getDetailEventItem(value!!) { eventData ->

                //Load data for event's detail activity given the eventID from HomeActivity
                //Through a get-API call
                val eventTitle = eventData.name
                val eventCategory = eventData.category_id
                val eventImg = eventData.image
                val eventDescription = eventData.description
                eventDate = eventData.date
                eventLocation = eventData.location
                ticketsTotal = eventData.tickets_total
                ticketsRemaining = eventData.tickets_remaining
                eventPrice = eventData.price
                eventTime = eventData.time


                binding.eventTitle.text = eventTitle
                binding.eventDate.text = eventDate
                binding.eventLocation.text = eventLocation
                binding.eventCategory.text = eventCategory
                binding.eventDescription.text = eventDescription
                Glide.with(this@DetailEventActivity).load(eventImg).centerCrop().into(binding.imageEvent)


                //Set TICKETS button unclickable in case event is sold out
                if (ticketsRemaining <= 0){
                    binding.tickets.isEnabled = false
                    binding.tickets.setText("SOLD OUT")
                    binding.tickets.setBackgroundColor(Color.BLACK)
                }
            }
        }


        val tickets = binding.tickets
        val intent = Intent(this, BookTicketsActivity::class.java)

        //Navigation and Data Transfer
        tickets.setOnClickListener {

            intent.putExtra("ticketsTotal", ticketsTotal)
            intent.putExtra("ticketsRemaining", ticketsRemaining)
            intent.putExtra("price", eventPrice)
            intent.putExtra("date", eventDate)
            intent.putExtra("time", eventTime)
            intent.putExtra("location", eventLocation)

            this.startActivity(intent)

        }


    // To be done: 1. butttonListener
    //             2. SOLD OUT
    //             3. navigation from results avtivity










        /*

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


         */
    }
}


