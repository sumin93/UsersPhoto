package ru.sumin.usersphoto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.sumin.usersphoto.pojo.Photo

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    var photos: List<Photo> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.photo_item,
            parent,
            false
        )
        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPhoto: ImageView = itemView.findViewById(R.id.imageViewPhoto)
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)

        fun bind(photo: Photo) {
            textViewTitle.text = photo.title
            loadPhotoIntoImageView(photo.url, imageViewPhoto)
        }

        private fun loadPhotoIntoImageView(url: String, imageView: ImageView) {

        }
    }
}
