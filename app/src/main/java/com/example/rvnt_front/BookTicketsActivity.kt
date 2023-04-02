package com.example.rvnt_front

import android.app.AlertDialog
import android.app.Dialog
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



        //Creation of successfully booking tickets as pop up message
        val book = binding.bookTicketButton

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





        //testing NumberPicker
        binding.ticketsNumberPicker.minValue = 1
        binding.ticketsNumberPicker.maxValue = 10
        binding.ticketsNumberPicker.wrapSelectorWheel = true








    }
}