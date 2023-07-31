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
    private var selectEvent = EventSelectorAdapter.SelectEvent(ArrayList(), ArrayList(), "", 0, 0, 0)
    private var recyclerView: RecyclerView? = null
    private var cardBookAdapter: EventSelectorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listeners for user's fullName and e-mail input
        fullNameFocusListener()
        emailFocusListener()




        //Take attributes for event. Event's details are required for ticket's selection in RecyclerView.
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

            /* Check for remaining tickets' availability in NumberPicker and set limits in tickets' number
             * that can be bought.
             */
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
        val back = binding.backButton



        //Set book button
        val name = binding.nameEdit.text.toString()
        val email = binding.emailEdit.text


        //book.isEnabled = name.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()


        //Set a clickListener for Book button
        //Where a success message will be displayed
        book.setOnClickListener {


            val view = View.inflate(this@BookTicketsActivity, R.layout.activity_success_message, null)
            val builder = AlertDialog.Builder(this@BookTicketsActivity)

            builder.setView(view)

            val  dialog = builder.create()


            //Enable button only for valid input
            if (bookForm()) {
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                //close popup message
                val okButton = view.findViewById<Button>(R.id.okButton)
                okButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }





        //Set a clickListener for Cancel button
        //in order to navigate to DetailEventActivity
        cancel.setOnClickListener {

            this.onBackPressed()
        }

        //
        back.setOnClickListener {

            this.onBackPressed()
        }

    }




    // Set up cardView
    private fun setupCard(selectEvent: EventSelectorAdapter.SelectEvent){

        recyclerView = binding.datesList
        cardBookAdapter = EventSelectorAdapter(this@BookTicketsActivity, selectEvent)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = cardBookAdapter
    }

    // Listeners for user's e-mail input
    private  fun  emailFocusListener(){
        binding.emailEdit.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.emailTil.helperText = validEmail()
            }
        }
    }

    // Checks for valid user's e-mail input
    private  fun  validEmail(): String? {
        val email = binding.emailEdit.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Invalid Email Address"
        }

        return  null
    }

    // Listeners for user's fullName input
    private  fun  fullNameFocusListener(){
        binding.nameEdit.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.nameTil.helperText = validFullName()
            }
        }
    }

    // Checks for valid username's input
    private  fun  validFullName(): String? {
        val fullName = binding.nameEdit.text.toString()
        if (fullName.isEmpty()){
            return "Invalid full name input"
        }
        return  null
    }

    // A function based that disables button in case of invalid input
    // or resets form in case of book button click
    private fun bookForm(): Boolean {
        binding.nameTil.helperText = validFullName()
        binding.emailTil.helperText = validEmail()

        val validName = binding.nameTil.helperText == null
        val validEMail = binding.emailTil.helperText == null

        return if (validName && validEMail) {
            resetForm()
            true
        }else {
            invalidForm()
            false
        }
    }

    // Disables book button when form is invalid completed
    private  fun invalidForm(){

        binding.bookTicketButton.isEnabled = false
    }

    // Resets form when book button is clicked
    private  fun resetForm(){

        binding.nameEdit.text = null
        binding.emailEdit.text = null

            binding.nameTil.helperText = getString(R.string.Required)
            binding.emailTil.helperText = getString(R.string.Required)

        binding.bookTicketButton.isEnabled = true

    }

}