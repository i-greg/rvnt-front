package com.example.rvnt_front

/* A UI for event's detail*/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvnt_front.databinding.ActivityDetailEventBinding
import com.bumptech.glide.Glide

class DetailEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}


