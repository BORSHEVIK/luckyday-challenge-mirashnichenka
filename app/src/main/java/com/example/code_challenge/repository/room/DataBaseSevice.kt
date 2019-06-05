package com.example.code_challenge.repository.room

import android.annotation.SuppressLint
import android.content.Context
import com.example.code_challenge.model.Question
import com.example.code_challenge.model.Result
import com.example.code_challenge.repository.Repository
import com.example.code_challenge.repository.room.entity.CodeChalangeDatabase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DataBaseSevice(val context: Context) : Repository {

    private val codeChalangeDatabase: CodeChalangeDatabase

    init {
        codeChalangeDatabase = CodeChalangeDatabase.getInstance(context)
    }

    override fun getResults(): Single<List<Result>> {
        return codeChalangeDatabase.resultDao().getAll()
            .flatMap {
                val results = mutableListOf<Result>()
                for (result in it) {
                    results.add(Result(result.question, result.ansver, result.correctAnsver, result.ansverResult))
                }
                Single.just(results)
            }
    }

    override fun getQuestions(amount: Int): Single<List<Question>> {
        //DO nothing
        return Single.just(arrayListOf())
    }

    @SuppressLint("CheckResult")
    override fun addResult(result: Result) {
        val resultDB = com.example.code_challenge.repository.room.entity.Result()
            .apply {
                question = result.question
                ansver = result.ansver
                correctAnsver = result.correctAnsver
                ansverResult = result.ansverResult
            }

        Single.just(resultDB)
            .observeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe { result ->
                run {
                    codeChalangeDatabase.resultDao().insert(result)
                }
            }
    }

}