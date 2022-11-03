package com.example.travelapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CitylistAdapter(private val citylists: List<City>) : RecyclerView.Adapter<CitylistAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val cityitemcityTV: TextView
        val cityitemcountryTV: TextView
        val cityitemIV: ImageView


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            cityitemcityTV = itemView.findViewById(R.id.cityitemcityTV)
            cityitemcountryTV = itemView.findViewById(R.id.cityitemcountryTV)
            cityitemIV = itemView.findViewById(R.id.cityitemIV)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.city_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val citylist = citylists[position]
        // Set item views based on views and data model
        val cityitemcityTV = holder.cityitemcityTV
        cityitemcityTV.setText(citylist.cityName)
        val cityitemcountryTV = holder.cityitemcountryTV
        cityitemcountryTV.setText(citylist.countryName)
        val cityitemIV = holder.cityitemIV
        //cityitemIV.setImageBitmap()
        //Glide.with(this)
        //    .load("https://image.tmdb.org/t/p/w500/" + baseResult.known_for?.get(0)?.poster_path)
        //    .into(mediaImageView)

    }

    override fun getItemCount(): Int {
        return citylists.size
    }
}