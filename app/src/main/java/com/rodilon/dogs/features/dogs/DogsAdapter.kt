package com.rodilon.dogs.features.dogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.rodilon.dogs.R
import com.squareup.picasso.Picasso

private const val WIDTH = 300
private const val HEIGHT = 300
class DogsAdapter
constructor(
    private var list: List<String>,
    private val listener: ZoomImageListener
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

        holder.itemView.setOnClickListener {
            listener.onClickListener(list[position])
        }
    }

    class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var imageViewBreed: AppCompatImageView? = null

        init {
            imageViewBreed = itemView.findViewById(R.id.imageViewDog)
        }

        fun setImage(image: String) {

            Picasso.get()
                .load(image)
                .resize(WIDTH, HEIGHT)
                .centerCrop()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)
                .into(imageViewBreed)
        }
    }

    interface ZoomImageListener {
        fun onClickListener(url: String)
    }
}
