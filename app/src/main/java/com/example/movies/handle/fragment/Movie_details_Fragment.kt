package com.example.movies.handle.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.movies.Objects.Movie
import com.example.movies.R

import com.example.movies.handle.helper.Helpers
import com.example.movies.viewmodels.Details_model


class Movie_details_Fragment : Fragment() {
var model:Details_model ?=null
    lateinit var toolbar:Toolbar ; lateinit var name:TextView
    lateinit var desc:TextView ; lateinit var cost:TextView
    lateinit var images:ViewPager ; lateinit var rating:TextView; lateinit var year:TextView
lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
if (!requireArguments().isEmpty&&requireArguments().containsKey("movie")){
    movie=requireArguments().getSerializable("movie") as Movie
}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        name=view.findViewById(R.id.name)as TextView
        year=view.findViewById(R.id.year)as TextView
        rating=view.findViewById(R.id.rating)as TextView
        cost=view.findViewById(R.id.cost)as TextView
        desc=view.findViewById(R.id.desc_details)as TextView
        images=view.findViewById(R.id.images)as ViewPager
        Helpers.changeToolbar(requireActivity(),movie.title,toolbar)
    }

    override fun onResume() {
        super.onResume()
model= Details_model(requireActivity(),this@Movie_details_Fragment)
        model!!.callapis(movie)
    }

    override fun onDetach() {
        super.onDetach()
        model!!.cancelapi()
    }
    companion object {


        @JvmStatic
        fun newInstance() =
            Movie_details_Fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}