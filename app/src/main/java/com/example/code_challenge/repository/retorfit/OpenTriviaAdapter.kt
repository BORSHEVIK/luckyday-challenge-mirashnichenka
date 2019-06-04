package com.example.code_challenge.repository.retorfit

import com.example.code_challenge.repository.retorfit.model.GetQuestionsResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaAdapter {

    @GET("/api.php")
    fun getQuestions(@Query("amount") amount: Int, @Query("type") type: String): Single<GetQuestionsResponse>

}