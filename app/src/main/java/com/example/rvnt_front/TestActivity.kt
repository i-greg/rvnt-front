package com.example.rvnt_front

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.rvnt_front.api.ApiInterface
import com.example.rvnt_front.databinding.ActivityTestBinding
import com.example.rvnt_front.models.CarouselDataItem
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://rvnt-api.onrender.com/"
class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("cityId")
            //The key argument here must match that used in the other activity
            binding.tvTextView.text = value
        }

        getMyData(binding.tvTextView)

    }

    private fun getMyData(myTv: TextView) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCarouselData()

        retrofitData.enqueue(object : Callback<List<CarouselDataItem>?> {
            override fun onResponse(
                call: Call<List<CarouselDataItem>?>,
                response: Response<List<CarouselDataItem>?>
            ) {
                val responseBody = response.body()!!

                val myStingBuilder = StringBuilder()

                for (responseData in responseBody){
                    myStingBuilder.append(responseData.name)
                    myStingBuilder.append("\n")
                }

                //myTv.text = myStingBuilder

            }

            override fun onFailure(call: Call<List<CarouselDataItem>?>, t: Throwable) {
                Log.d("TestActivity", "onFailure"+t.message)
            }
        })
    }
}