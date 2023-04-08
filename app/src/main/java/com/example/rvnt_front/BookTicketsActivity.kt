package com.example.rvnt_front

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import com.example.rvnt_front.databinding.ActivityDetailEventBinding
import com.bumptech.glide.Glide
import com.example.rvnt_front.databinding.ActivityBookTicketsBinding
import android.widget.Toast

class BookTicketsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookTicketsBinding
    private lateinit var bookTicketButton: Button              // A variable for Book button
    private lateinit var cancelTicketButton: Button            // A variable for Cancel button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        val extras = intent.extras
        if (extras != null) {

            val ticketsTotal = extras.getInt("ticketsTotal")
            val ticketsRemaining = extras.getInt("ticketsRemaining")
            val eventPrice = extras.getInt("price")
            val ticketsDate = extras.getString("date")
            val eventTime = extras.getString("time")
            val location = extras.getString("location")

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
}