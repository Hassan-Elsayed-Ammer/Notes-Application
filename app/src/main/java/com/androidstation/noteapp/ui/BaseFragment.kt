package com.androidstation.noteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//abstract base fragment class to extends Fragment and implement CoroutineScope interface

abstract class BaseFragment :Fragment(), CoroutineScope {

    //job is background task Coroutine
    private lateinit var job: Job


    override val coroutineContext: CoroutineContext
        //Dispatchers is define the thread to do our background job
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }



}