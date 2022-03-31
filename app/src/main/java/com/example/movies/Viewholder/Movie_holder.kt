package com.example.movies.Viewholder

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movies.Objects.Movie
import com.example.movies.R
import com.example.movies.handle.fragment.Movie_details_Fragment
import com.example.movies.viewmodels.Movies_model

class Movie_holder (view: View, var activity: FragmentActivity, var model: Movies_model) : RecyclerView.ViewHolder(view) {
    var image:ImageView?=null;var name:TextView?=null;var date:TextView?=null;var movielayout: ConstraintLayout?=null
    init {
image=view.findViewById(R.id.image)as ImageView
        name=view.findViewById(R.id.name)as TextView
        date=view.findViewById(R.id.date)as TextView
        movielayout=view.findViewById(R.id.movie)as ConstraintLayout
    }
    fun bindPhoto(cnt: Context?, photoUrl: String?) {
        val myOptions: RequestOptions = RequestOptions()
            .override(200, 200)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
        Glide.with(cnt!!)
            .asBitmap()
            .load(photoUrl)
            .apply(myOptions)
            .into(image!!)
    }
    fun handlerow(movie: Movie){
        name!!.text=movie.title
        date!!.text=movie.date
       bindPhoto(activity,movie.poster_path)
        movielayout!!.setOnClickListener(movieclick(movie))
    }

    fun movieclick(movie: Movie):View.OnClickListener{
        return View.OnClickListener {
            val frag: Fragment = Movie_details_Fragment.newInstance()
            val bund = Bundle()

            bund.putSerializable("movie", movie)

            frag.arguments = bund
            activity.supportFragmentManager.beginTransaction().replace(
                R.id.container,
                frag, "Details"
            ).addToBackStack("Details").commit()
        }
    }

}