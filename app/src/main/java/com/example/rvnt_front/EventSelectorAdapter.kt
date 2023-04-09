package com.example.rvnt_front


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class EventSelectorAdapter (private val getActivity: BookTicketsActivity, private val selectEvent: SelectEvent):
    RecyclerView.Adapter<EventSelectorAdapter.MyViewHolder>(){

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_event_card, parent, false)

        return MyViewHolder(view)
    }


    //
    override fun getItemCount(): Int {


        return selectEvent.date.size * selectEvent.time.size
    }


    //
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var datesAndTimes = ArrayList<DatesTimes>()

        for (date in selectEvent.date){
            for(time in selectEvent.time){
                val it =DatesTimes(date, time)
                datesAndTimes.add(it)
            }
        }

        holder.eventDate.text = datesAndTimes[position].date
        holder.eventTime.text = datesAndTimes[position].time
        holder.location.text = selectEvent.location
        holder.price.text= selectEvent.price.toString()

        // ADD PROGRESSBAR

        holder.cardView.setOnClickListener{
            holder.cardView.setCardBackgroundColor(Color.GRAY)
        }
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val eventDate : TextView = itemView.findViewById(R.id.eventDate)
        val eventTime : TextView = itemView.findViewById(R.id.eventTime)
        val location : TextView = itemView.findViewById(R.id.eventPlace)
        val price : TextView = itemView.findViewById(R.id.ticketPrice)
        val cardView : CardView = itemView.findViewById(R.id.bookEventCard)


    }

    data class SelectEvent(
        var date: ArrayList<String>,
        var time: ArrayList<String>,
        var location: String,
        var price: Int,
        var ticketsRemaining: Int,
        var ticketsTotal : Int
    )

    data class  DatesTimes(

        var date: String,
        var time: String
    )

}