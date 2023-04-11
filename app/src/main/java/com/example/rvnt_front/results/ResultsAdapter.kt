/*package com.example.rvnt_front.results

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rvnt_front.databinding.ItemResultBinding


class ResultsAdapter(val context: Context, val resultsList: List<com.example.rvnt_front.models.DetailEventItem>) : RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {


    inner class ResultsViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

        // 31/3/2023
       // This is for navigating in DetailEvent Activity
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
               if (position != RecyclerView.NO_POSITION) {
                 listener?.onItemClick(results[position])
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<DetailEventItem>() {
        override fun areItemsTheSame(oldItem: DetailEventItem, newItem: DetailEventItem): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: DetailEventItem, newItem: DetailEventItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    // 31/3/2023
    // Listener for the Item
   private var listener: AdapterView.OnItemClickListener? = null


   var results: List<DetailEventItem>
        get() = differ.currentList
        set(value) { differ.submitList(value) }



    override fun getItemCount() = resultsList.size  // results

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(ItemResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {



        holder.binding.apply {
            val result = resultsList[position] // results
            tvTitle.text = result.name
            tvDate.text = result.date[0]
            tvCity.text = result.location
            // 31/3/2023
            // Set the click listener to open the DetailEventActivity with the selected ResultsItem
            root.setOnClickListener { listener?.onItemClick(result) }
        }
    }

    // 31/3/2023
    // Setting the interface for the click listener
    interface OnItemClickListener : AdapterView.OnItemClickListener {
        abstract val retrofitBuilder: Any

        fun onItemClick(result: DetailEventItem)
    }

    // 31/3/2023
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }


}

/*
// initializing the binding
binding = ActivityDisplayResultsBinding.inflate(layoutInflater)
setContentView(binding.root)
setupRecyclerView()


// 9/4/23 - Taking categoryID from CardViewAdapter

/*
 val extras = intent.extras
 // Bind the data to the views
binding.apply {
    tvTitle.text = selectedResult?.title
    tvDate.text = selectedResult?.date
    tvCity.text = selectedResult?.city
    tvDescription.text = selectedResult?.description
    Glide.with(this@DetailEventActivity).load(selectedResult?.imageUrl).into(ivEvent)
}
 */


val extras = intent.extras
if (extras != null) {
    val value = extras.getString("event_id")
    val apiManager = ApiManager()
    apiManager.getDetailEventItem(value!!) { eventData ->

        binding.tvTit

        binding.apply {
            tvT
            tvTitle.text = selectedResult?.title
            tvDate.text = selectedResult?.date
            tvCity.text = selectedResult?.city
            tvDescription.text = selectedResult?.description
            Glide.with(this@DetailEventActivity).load(selectedResult?.imageUrl)
                .into(ivEvent)
        }

        //Load data for event's detail activity given the eventID from HomeActivity
        //Through a get-API call
        // val eventTitle = eventData.name

        //val eventImg = eventData.image
        //val eventDescription = eventData.description
        //eventDate = eventData.date
        //eventLocation = eventData.location
        //ticketsTotal = eventData.tickets_total
        //ticketsRemaining = eventData.tickets_remaining
        //eventPrice = eventData.price
        //eventTime = eventData.time[0]


        /*
        binding.eventTitle.text = eventTitle
        binding.eventDate.text = eventDate[0]
        binding.eventLocation.text = eventLocation
        binding.eventCategory.text = eventCategory.category
        binding.eventDescription.text = eventDescription
        Glide.with(this@DetailEventActivity).load(eventImg).centerCrop().into(binding.imageEvent)
*/



    }
}

 */