package com.example.rvnt_front


import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import com.example.rvnt_front.databinding.ActivityBookTicketsBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BookTicketsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookTicketsBinding
    private lateinit var bookTicketButton: Button              // A variable for Book button
    private lateinit var cancelTicketButton: Button            // A variable for Cancel button
    private var selectEvent = EventSelectorAdapter.SelectEvent(ArrayList(), ArrayList(), "", 0, 0, 0)
    private var recyclerView: RecyclerView? = null
    private var cardBookAdapter: EventSelectorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        fullNameFocusListener()
        emailFocusListener()
        binding.bookTicketButton.setOnClickListener { bookForm() }



        //Take attributes for event
        val extras = intent.extras
        if (extras != null) {

            val ticketsTotal = extras.getInt("ticketsTotal")
            val ticketsRemaining = extras.getInt("ticketsRemaining")
            val eventPrice = extras.getInt("price")
            val ticketsDate = extras.getStringArrayList("date") as ArrayList<String>
            val eventTime = extras.getStringArrayList("time")  as ArrayList<String>
            val location = extras.getString("location") as String



            selectEvent.date = ticketsDate
            selectEvent.time = eventTime
            selectEvent.location = location
            selectEvent.price = eventPrice
            selectEvent.ticketsRemaining = ticketsRemaining
            selectEvent.ticketsTotal = ticketsTotal

            setupCard(selectEvent)


            //NumberPicker
            //Initialize minimumValue
            binding.ticketsNumberPicker.minValue = 1

            //Check for remaining tickets' availability in NumberPicker
            if (ticketsRemaining < 10) {
                binding.ticketsNumberPicker.maxValue = ticketsRemaining
            }else{
                binding.ticketsNumberPicker.maxValue = 10
            }

            binding.ticketsNumberPicker.wrapSelectorWheel = true
            binding.ticketsNumberPicker.setOnValueChangedListener { numberPicker, oldValue, newValue2 -> binding.ticketsNumber.text = "Booking Tickets: $newValue2"  }
        }


        //Creation of successfully booking tickets as pop up message
        val book = binding.bookTicketButton
        val cancel = binding.cancelTicketButton

        //Set a clickListener for Book button
        //Where a success message will be displayed
        book.setOnClickListener {

            val view = View.inflate(this@BookTicketsActivity, R.layout.activity_success_message, null)
            val builder = AlertDialog.Builder(this@BookTicketsActivity)

            builder.setView(view)

            val  dialog = builder.create()

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }


        //Set a clickListener for Cancel button
        //In order to navigate to DetailEventActivity
        cancel.setOnClickListener {

            this.onBackPressed()
        }

    }


    //
    private fun setupCard(selectEvent: EventSelectorAdapter.SelectEvent){
        //CardView Functionality
        //selectEvent = EventSelectorAdapter.SelectEvent()

        recyclerView = binding.datesList
        cardBookAdapter = EventSelectorAdapter(this@BookTicketsActivity, selectEvent)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = cardBookAdapter
    }

    //
    private  fun  emailFocusListener(){
        binding.emailText.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.email.helperText = validEmail()
            }
        }
    }

    //
    private  fun  validEmail(): String? {
        val email = binding.emailText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Invalid Email Address"
        }

        return  null
    }

    //
    private  fun  fullNameFocusListener(){
        binding.fullNameText.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.fullName.helperText = validFullName()
            }
        }
    }

    //
    private  fun  validFullName(): String? {
        val fullName = binding.fullNameText.text.toString()
        if (fullName.length==0){
            return "Invalid full name input"
        }

        return  null
    }

    //
    private fun bookForm(){
        binding.fullName.helperText = validFullName()
        binding.email.helperText = validEmail()

        val validName = binding.fullName.helperText == null
        val validEMail = binding.email.helperText == null

        if (validName && validEMail)
            resetForm()
        else
            invalidForm()

    }

    //
    private  fun invalidForm(){

        binding.bookTicketButton.isEnabled = false
    }

    //
    private  fun resetForm(){

                binding.fullNameText.text = null
                binding.emailText.text = null

            //    binding.fullName.helperText = getString(R.string.Required)
            //    binding.email.helperText = getString(R.string.Required)

        binding.bookTicketButton.isEnabled = true

    }

}