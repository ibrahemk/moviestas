package com.example.movies.Objects

import java.io.Serializable

class Movie : Serializable {
    lateinit var id:String
    lateinit var title:String
    lateinit var date:String
    lateinit var poster_path:String
    lateinit var vote:String
    lateinit var overview:String
    lateinit var budget:String
    lateinit var backdrop_path:String

}