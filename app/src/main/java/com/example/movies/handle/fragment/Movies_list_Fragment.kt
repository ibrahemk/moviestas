package com.example.movies.handle.fragment

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.viewmodels.Movies_model


class Movies_list_Fragment : Fragment() {
lateinit var search:EditText
lateinit var searchicon:ImageView
lateinit var typeslist:RecyclerView
lateinit var movieslist:RecyclerView
lateinit var model:Movies_model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search=view.findViewById(R.id.search)as EditText
        searchicon=view.findViewById(R.id.searchicon)as ImageView
        typeslist=view.findViewById(R.id.typeslist)as RecyclerView
        movieslist=view.findViewById(R.id.movieslist)as RecyclerView
        model= Movies_model(requireActivity(),this@Movies_list_Fragment)
    }

    override fun onResume() {
        super.onResume()
        model.callapis(0)
        model.handleview()
    }
    companion object {


        @JvmStatic
        fun newInstance() =
            Movies_list_Fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}