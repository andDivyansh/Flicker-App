package com.divyansh.flicker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.divyansh.flicker.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_content.view.*

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
class FlickerAdapter : RecyclerView.Adapter<FlickerAdapter.PhotoViewHolder>() {

    private var imageUrls = mutableListOf<String>()

    fun setUrls(url: MutableList<String>) {
        imageUrls.addAll(url)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_content, parent, false))


    override fun getItemCount(): Int = imageUrls.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindItem(imageUrls[position])
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(
            flickerImage: String
        ) {
            Picasso.get().load(flickerImage).into(itemView.item_image)
        }
    }
}
