package com.example.sillappsproject.ui.main.fragments.details.ui.detailfragment2

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sillapps.data.model.Album
import com.example.sillappsproject.R
import com.example.sillappsproject.common.showError
import com.example.sillappsproject.data.api.SillappsProvider
import com.example.sillappsproject.data.model.Photo
import com.example.sillappsproject.ui.main.fragments.main.MainAdapter

class DetailFragment2 : Fragment() {

    private  val adapter: DetailFragment2Adapter by lazy { DetailFragment2Adapter() }
    private lateinit var detailRecyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("frag",arguments?.getInt("id").toString())
        return inflater.inflate(R.layout.detail_fragment2_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailRecyclerView = view.findViewById(R.id.detailRecycler)

        initRecyclerView()
        getPhotos()


    }

    private fun getPhotos() {
        Log.d("gat","gat")

        SillappsProvider.getPhotos(arguments!!.getInt("id"),object :SillappsProvider.Listener<List<Photo>>{
            override fun onError(t: Throwable) {
                showError(t)
            }

            override fun onSuccess(data: List<Photo>) {
                adapter.dataDetail = data as MutableList<Photo>
                adapter.context = activity?.applicationContext
            }

        })
    }

    private fun initRecyclerView() {
        detailRecyclerView.layoutManager = LinearLayoutManager(context)
        detailRecyclerView.adapter = adapter
        detailRecyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

    }

}