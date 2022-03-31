package com.example.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.handle.fragment.Movies_list_Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, Movies_list_Fragment.newInstance(), "home").commit()

    }
}