package com.example.rvnt_front

/* A UI for event's detail*/
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.rvnt_front.databinding.ActivityDetailEventBinding
import com.bumptech.glide.Glide
import com.example.rvnt_front.api.ApiManager
import com.example.rvnt_front.models.CityItem

class DetailEventActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailEventBinding
    private var ticketsTotal = 0
    private var ticketsRemaining = 0
    private var eventPrice = 0
    private var eventDate = ArrayList<String>()
    private var eventTime = ArrayList<String>()
    private var eventLocation = ""
    private var cityId: CityItem = CityItem("","")







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

                //Initialize variables for BookTicketsActivity
                eventDate = eventData.date
                eventLocation = eventData.location
                ticketsTotal = eventData.tickets_total
                ticketsRemaining = eventData.tickets_remaining
                eventPrice = eventData.price
                eventTime = eventData.time

                //Send variables to BookTicketsActivity
                binding.eventTitle.text = eventTitle
                binding.eventDate.text = eventDate[0]
                binding.eventLocation.text = eventLocation
                binding.eventCategory.text = eventCategory.category
                binding.eventDescription.text = eventDescription


                Glide.with(this@DetailEventActivity).load(eventImg).optionalCenterCrop().into(binding.imageEvent)


                //Set TICKETS button unclickable in case event is sold out
                if (ticketsRemaining <= 0){
                    binding.tickets.isEnabled = false
                    binding.tickets.setText("SOLD OUT")
                    binding.tickets.setBackgroundColor(Color.GRAY)
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


    }
}


