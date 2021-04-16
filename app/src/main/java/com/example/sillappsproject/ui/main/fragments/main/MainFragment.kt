package com.example.sillappsproject.ui.main.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sillapps.data.model.Album
import com.example.sillappsproject.R
import com.example.sillappsproject.common.showError
import com.example.sillappsproject.data.api.SillappsProvider
import com.example.sillappsproject.data.model.FinalAlbum
import com.example.sillappsproject.data.model.Users

class MainFragment : Fragment() {

    private lateinit var albumRecyclerView: RecyclerView
    private val albumsAdapter: MainAdapter by lazy { MainAdapter() }
    private var dataAlbum: ArrayList<Album> = ArrayList()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumRecyclerView = view.findViewById(R.id.albumRecycler)

        initRecyclerView()
        getAlbums()
        getUser()
        Log.d("gat1",dataAlbum.size.toString())
    }

    private fun getAlbums() {
        Log.d("gat","gat")
        SillappsProvider.getAlbum(object : SillappsProvider.Listener2<List<Album>> {
            override fun onSuccess(data: List<Album>) {

                albumsAdapter.dataAlbum = data as MutableList<Album>

                Log.d("user",data.size.toString())

            }

            override fun onError(t: Throwable) {
                showError(t)
            }


        })
    }
    private fun getUser() {
        SillappsProvider.getUser(object : SillappsProvider.Listener<List<Users>> {
            override fun onSuccess(data: List<Users>) {


                albumsAdapter.dataUser = data as ArrayList<Users>


            }

            override fun onError(t: Throwable) {
                showError(t)
            }


        })

    }

    private fun initRecyclerView() {
        albumRecyclerView.layoutManager = LinearLayoutManager(context)
        albumRecyclerView.adapter = albumsAdapter
        albumRecyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

    }



}

