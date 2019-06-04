package com.example.code_challenge.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.code_challenge.R
import com.example.code_challenge.ResultsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var resultsAdapter: ResultsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.recycler_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        resultsAdapter = ResultsAdapter(recyclerView.context)
        recyclerView.adapter = resultsAdapter

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)

        swipeRefreshLayout.setOnRefreshListener({
            update()
        })

        return view
    }

    override fun onResume() {
        super.onResume()

        update()
    }

    fun update() {
        abs?.getRepository()?.getResults()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ result ->
                swipeRefreshLayout.setRefreshing(false)
                resultsAdapter.items = result.toMutableList()
            },
                {error ->
                    swipeRefreshLayout.setRefreshing(false)
                })
    }
}