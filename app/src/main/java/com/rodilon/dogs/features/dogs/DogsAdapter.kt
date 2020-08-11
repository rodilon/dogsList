package com.rodilon.dogs.features.dogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.rodilon.dogs.R
import com.squareup.picasso.Picasso

class DogsAdapter
constructor(
    private var list: List<String>
) : RecyclerView.Adapter<DogsAdapter.BreedsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        return BreedsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dogs_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        holder.setImage(list[position])
    }

    class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var imageViewBreed: AppCompatImageView? = null

        init {
            imageViewBreed = itemView.findViewById(R.id.imageViewDog)
        }

        fun setImage(image: String) {

            Picasso.get().setIndicatorsEnabled(true)

            Picasso.get()
                .load(image)
                .resize(300, 250)
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)
                .into(imageViewBreed)
        }
    }
}
