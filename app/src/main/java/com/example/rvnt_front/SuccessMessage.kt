package com.example.rvnt_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvnt_front.databinding.ActivityDetailEventBinding
import com.bumptech.glide.Glide
import com.example.rvnt_front.databinding.ActivitySuccessMessageBinding

class SuccessMessage : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessMessageBinding

    // A function for pop-up message when user books tickets successfully
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}