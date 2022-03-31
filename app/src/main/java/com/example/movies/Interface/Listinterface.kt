package com.example.movies.Interface

import android.view.View
import android.widget.TextView
import com.example.movies.Objects.Genres
import com.example.movies.Objects.Movie
import com.example.movies.handle.AsyncRq
import java.util.*
import kotlin.collections.ArrayList

interface Listinterface {
    var templimit: Int
    var moviestype: Int
    var searcha: Boolean
    var masync: AsyncRq<String,Objects>?
    fun handleview()
    fun callapis(type:Int)
    fun setgadapter(list:ArrayList<Genres>)
    fun setmadapter(list:ArrayList<Movie>)
    fun handle_scroll()
    fun searchfoucs(): View.OnFocusChangeListener
    fun searchaction():View.OnClickListener
    fun parsetypes(result:String):ArrayList<Genres>
    fun parsemovies(result:String):ArrayList<Movie>
}
