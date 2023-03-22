package com.example.rvnt_front

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import com.example.rvnt_front.databinding.ActivityHomeBinding
import com.google.gson.Gson
import kotlin.collections.ArrayList


class CarouselHelper(private val viewPager2: ViewPager2, private val binding: ActivityHomeBinding) {

    //Carousel handling
    private lateinit var runnable: Runnable
    private val handler = Handler(Looper.getMainLooper())

    //Carousel behavior inputs
    private var isAutoScrolling = true
    private val AUTO_SCROLL_DELAY = 3000L // 3 seconds

    //Carousel data lists
    private var titlesList: ArrayList<String> = ArrayList()
    private var descList: ArrayList<String> = ArrayList()
    private var imagesList: ArrayList<String> = ArrayList()


    fun setupCarousel() {

        //Adding carousel data to lists from json (now) need API response (future)
        postToCarouselList()

        //Setting viewpager
        viewPager2.adapter = ViewPagerAdapter(titlesList, descList, imagesList)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //Setting indicator
        val indicator = binding.indicator
        indicator.setViewPager(viewPager2)

        //Schedule auto scrolling
        runnable = Runnable {
            if (isAutoScrolling) {
                viewPager2.currentItem = (viewPager2.currentItem + 1) % viewPager2.adapter!!.itemCount
            }
            handler.postDelayed(runnable, AUTO_SCROLL_DELAY)
        }
        handler.postDelayed(runnable, AUTO_SCROLL_DELAY)

        // Pause auto scrolling when user interacts with the ViewPager
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                isAutoScrolling = state == ViewPager2.SCROLL_STATE_IDLE
            }
        })

    }

    private fun addToCarouselList(title: String, description: String, image: String) {
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    private fun postToCarouselList() {
        //need to get data from HomeActivity!!!!! probably :/
        val jsonFileString = binding.root.context.assets.open("imagedata.json").bufferedReader().use { it.readText() }
        val imageResponse = Gson().fromJson(jsonFileString, ImageResponse::class.java)


        for (imageData in imageResponse.images) {
            addToCarouselList(imageData.title, imageData.description, imageData.image)
        }
    }

    fun stopAutoScrolling() {
        handler.removeCallbacks(runnable)
    }

    data class ImageData(
        val title: String,
        val description: String,
        val image: String
    )

    data class ImageResponse(
        val images: List<ImageData>
    )

}

