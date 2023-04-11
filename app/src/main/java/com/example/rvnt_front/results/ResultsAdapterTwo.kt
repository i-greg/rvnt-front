package com.example.rvnt_front

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.rvnt_front.databinding.ItemResultBinding
import com.example.rvnt_front.models.ResultsItem
import com.example.rvnt_front.results.DisplayResultsActivity


class ResultsAdapterTwo(private val getActivity: DisplayResultsActivity, private val eventList: List<ResultsItem>) : RecyclerView.Adapter<ResultsAdapterTwo.ResultsViewHolder>() {


    inner class ResultsViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

        // 31/3/2023
       // This is for navigating in DetailEvent Activity
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(eventList[position])
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    // 31/3/2023
    // Listener for the Item
    private var listener: OnItemClickListener? = null

    //var results: List<ResultsItem>

       // get() = differ.currentList
        //set(value) { differ.submitList(value) }



    override fun getItemCount() = eventList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(ItemResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.binding.apply {
            val result = eventList[position]
            tvTitle.text = result.name
            tvDate.text = result.date[0]
            tvCity.text = result.location
            Glide.with(getActivity).load(result.image).centerCrop().into(ivImage)
            // 31/3/2023
            // Set the click listener to open the DetailEventActivity with the selected ResultsItem
            root.setOnClickListener { listener?.onItemClick(result) }
        }
    }

    // 31/3/2023
    // Setting the interface for the click listener
    interface OnItemClickListener {
        fun onItemClick(result: ResultsItem)
    }

    // 31/3/2023
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }


}