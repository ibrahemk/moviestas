package com.example.movies.handle.helper

import androidx.test.espresso.IdlingResource




class EspressoIdlingResource {
    private val RESOURCE = "GLOBAL"

    val mCountingIdlingResource: SimpleCountingIdlingResource =
        SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource? {
        return mCountingIdlingResource
    }
}