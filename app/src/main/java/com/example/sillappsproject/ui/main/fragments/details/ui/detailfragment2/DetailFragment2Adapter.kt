package com.example.sillappsproject.ui.main.fragments.details.ui.detailfragment2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.example.sillappsproject.R
import com.example.sillappsproject.data.model.Photo
import com.squareup.picasso.Picasso


class DetailFragment2Adapter : RecyclerView.Adapter<DetailFragment2Adapter.DetailViewHolder>() {


    var context : Context? = null
    var dataDetail: MutableList<Photo> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: ((Photo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_item,parent,false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int =  dataDetail.size


    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.detailImageView)

    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val photo = dataDetail[position]
        Log.d("photos",dataDetail[1].title)


        with(holder) {


            itemView.setOnClickListener { listener?.invoke(photo) }
        }
    }


}
