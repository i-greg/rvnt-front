package com.example.rvnt_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvnt_front.databinding.ActivityTestBinding


class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("key")
            //The key argument here must match that used in the other activity
            binding.tvTextView.text = value
        }
    }
}