package com.example.rvnt_front

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ViewPagerAdapter (private var title : List<String>, private var _id : List<String>,
                        private var images : List<String>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {


    inner class Pager2ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val itemImage: ImageView = itemView.findViewById(R.id.ivImage)


        init {

            val intent = Intent(itemView.context, DetailEventActivity::class.java)


            itemImage.setOnClickListener {

                //Getting current position
                val position: Int = adapterPosition
                intent.putExtra("event_id", _id[position])
                itemView.context.startActivity(intent)

                //Toast.makeText(itemView.context, "you clicked on item #${details[position]}", Toast.LENGTH_SHORT).show()


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]


        Glide.with(holder.itemImage.context)
            .load(images[position])
            //Image fill
            .centerCrop()
            .into(holder.itemImage)
    }

}