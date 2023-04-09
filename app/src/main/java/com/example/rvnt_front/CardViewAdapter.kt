package com.example.rvnt_front

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rvnt_front.results.DisplayResultsActivity

class CardViewAdapter (private val getActivity: HomeActivity, private val eventList: List<Event>):
    RecyclerView.Adapter<CardViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvEventTitle.text = eventList[position].title
        //holder.ivEventImg.setImageResource(eventList[position].image)
        Glide.with(holder.ivEventImg.context)
            .load(eventList[position].image)
            //Image fill
            .centerCrop()
            .into(holder.ivEventImg)

        val intent = Intent(getActivity, DisplayResultsActivity::class.java)

        holder.cardView.setOnClickListener{

            intent.putExtra("category_id", eventList[position].id)
            getActivity.startActivity(intent)

            //Toast.makeText(getActivity, eventList[position].title, Toast.LENGTH_LONG).show()
        }
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvEventTitle : TextView = itemView.findViewById(R.id.tv_eventTitle)
        val ivEventImg : ImageView = itemView.findViewById(R.id.iv_nearImg)
        val cardView : CardView = itemView.findViewById(R.id.cardView)

    }

    data class Event(
        var title: String,
        var image: String,
        var id: String
    )

}