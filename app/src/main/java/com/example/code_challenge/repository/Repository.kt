package com.example.code_challenge.repository

import com.example.code_challenge.model.Question
import com.example.code_challenge.model.Result
import io.reactivex.Single

interface Repository {

    fun getResults(): Single<List<Result>>
    fun getQuestions(amount: Int): Single<List<Question>>
    fun addResult(result: Result)

}