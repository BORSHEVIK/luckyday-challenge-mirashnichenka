package com.example.code_challenge.repository

import android.content.Context
import com.example.code_challenge.model.Question
import com.example.code_challenge.model.Result
import com.example.code_challenge.repository.retorfit.RetrofitApi
import com.example.code_challenge.repository.room.DataBaseSevice
import io.reactivex.Single

class RepositoryImpl(val contecxt: Context) : Repository {

    private val retrofitApi: RetrofitApi
    private val dataBaseSevice: DataBaseSevice

    init {
        retrofitApi = RetrofitApi()
        dataBaseSevice = DataBaseSevice(contecxt)
    }

    override fun getResults(): Single<List<Result>> {
        return dataBaseSevice.getResults()
    }

    override fun getQuestions(amount: Int): Single<List<Question>> {
        return retrofitApi.getQuestions(amount)
    }

    override fun addResult(result: Result) {
        dataBaseSevice.addResult(result)
    }

}