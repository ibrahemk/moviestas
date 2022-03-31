package com.example.movies.Interface

import com.example.movies.Objects.Genres
import com.example.movies.Objects.Movie

interface Details_interface {
    fun callapis(movie: Movie)

    fun parsemovie(result:String):Movie
    fun cancelapi()
    fun handleui(movie: Movie)
}