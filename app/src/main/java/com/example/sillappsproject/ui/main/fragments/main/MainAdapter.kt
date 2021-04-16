package com.example.sillappsproject.ui.main.fragments.main

import android.content.Intent
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sillapps.data.model.Album
import com.example.sillappsproject.R
import com.example.sillappsproject.common.navigate
import com.example.sillappsproject.data.model.FinalAlbum
import com.example.sillappsproject.data.model.Users
import kotlinx.android.synthetic.main.item_album.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.AlbumViewHolder>() {


    var dataUser: ArrayList<Users> = ArrayList()
    var dataAlbum: MutableList<Album> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: ((Album) -> Unit)? = null

    override fun getItemCount() = dataAlbum.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        dataAlbum.sortBy { it.title }
        return AlbumViewHolder(view)
    }

    fun removeItem(position: Int) {
        dataAlbum.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataAlbum.size)
    }


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {

        val album = dataAlbum[position]
        val author = dataUser.find { it.id == album.userId }
        Log.d("album", album.title)
        Log.d("author", author?.name)
        with(holder) {
            titleTv.text = album.title
            authorTv.text = author?.name

        }
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("id" to album.id)
            it.findNavController().navigate(R.id.action_albumFragment_to_detailFragment,bundle)
        }
    }


    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var authorTv: TextView = itemView.findViewById(R.id.item_album_author)
        var titleTv: TextView = itemView.findViewById(R.id.item_album_name)


    }
}

