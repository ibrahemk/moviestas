package com.example.movies.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Objects.Movie
import com.example.movies.R
import com.example.movies.Viewholder.Movie_holder
import com.example.movies.viewmodels.Movies_model

class Movies_adapter (var activity: FragmentActivity, var model: Movies_model, var list:ArrayList<Movie>): RecyclerView.Adapter<Movie_holder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Movie_holder {
        return Movie_holder( LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)
            ,activity,model)
    }

    override fun onBindViewHolder(holder: Movie_holder, position: Int) {
        var m: Movie =list[position]
        holder.handlerow(movie = m)
    }

    override fun getItemCount(): Int {
     return list.size
    }

    fun setlimitlist(listt:ArrayList<Movie>){
        list.addAll(listt)
        notifyDataSetChanged()

    }
}