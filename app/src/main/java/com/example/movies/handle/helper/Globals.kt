package com.example.movies.handle.helper

object Globals {
    var Apikey="586d559c50c0b82d50f8992b20393a7d"
    var url="https://api.themoviedb.org/3"
    var moviedetails="$url/movie/"
//    https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&query=hulk&page=1&include_adult=false

    var search="$url/search/movie?"
    var imagew500="https://image.tmdb.org/t/p/w500"
    var imageoriginal="https://image.tmdb.org/t/p/original"
 var types="$url/genre/movie/list?"
//    https://api.themoviedb.org/3/discover/movie?api_key=###&with_genres=28
    var getMoviesbytype="$url/discover/movie?"

    var getpopular="$url/movie/popular?"
}