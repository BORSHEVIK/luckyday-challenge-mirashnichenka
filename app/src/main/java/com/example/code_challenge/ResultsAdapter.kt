package com.example.code_challenge

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.code_challenge.model.Result
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ResultsAdapter(val context: Context) : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    var items = mutableListOf<Result>()
        set(items){
            field = items
            filteredItems = items
        }

    private var filteredItems = mutableListOf<Result>()
        set(items){
            field = items
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.result_item, parent, false))
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = filteredItems.get(position)

        holder.question.text = result.question

        holder.answer.text = result.ansver

        holder.correctAnswer.text = result.correctAnsver

        holder.result.text = if(result.ansverResult) "correct".also { holder.result.setTextColor(context.resources.getColor(R.color.green)) }
        else "incorrect".also { holder.result.setTextColor(context.resources.getColor(R.color.red)) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rootView = view

        val question = view.findViewById<TextView>(R.id.question)

        val answer = view.findViewById<TextView>(R.id.answer)

        val correctAnswer = view.findViewById<TextView>(R.id.corrent_answer)

        val result = view.findViewById<TextView>(R.id.result)
    }
}