package com.example.rvnt_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.rvnt_front.databinding.ActivityDetailEventBinding
import com.bumptech.glide.Glide
import com.example.rvnt_front.databinding.ActivityBookTicketsBinding
import android.widget.Toast

class BookTicketsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookTicketsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //testing NumberPicker
        binding.ticketsNumberPicker.minValue = 1
        binding.ticketsNumberPicker.maxValue = 10
        binding.ticketsNumberPicker.wrapSelectorWheel = true


        binding.bookTicketButton.setOnClickListener{

            val email = binding.editEmail.text.toString().trim()

            if (email.isEmpty()){

                binding.editEmail.error = "Invalid Email"

            }else{


            }
        }


        /*
        binding.bookTicketButton.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding.editEmail.text.toString())
                        .matches()
                )
                    binding.bookTicketButton.isEnabled = true
                else {
                    binding.bookTicketButton.isEnabled = false
                    binding.bookTicketButton.error = "Invalid Email Address"
                }
            }
        }) */


    }
}