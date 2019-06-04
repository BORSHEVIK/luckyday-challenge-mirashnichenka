package com.example.code_challenge.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.code_challenge.Abs

abstract class BaseFragment : Fragment() {

    companion object {
        private const val FRAGMENT_STATE = "FRAGMENT_STATE"
    }

    internal var abs: Abs? = null
    internal var state: Bundle? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        abs = activity as Abs
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            state = savedInstanceState.getBundle(FRAGMENT_STATE)
        } else {
            state = Bundle()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        if (state != null) {
            outState.putBundle(FRAGMENT_STATE, state)
        }

        super.onSaveInstanceState(outState)
    }

}