package com.example.code_challenge.repository.retorfit

import com.example.code_challenge.model.Question
import com.example.code_challenge.model.Result
import com.example.code_challenge.repository.Repository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor



class RetrofitApi(): Repository {

    companion object {
        private const val BASE_URL = "https://opentdb.com"
    }

    private val retrofit: Retrofit

    private val openTriviaAdapter: OpenTriviaAdapter

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        openTriviaAdapter = retrofit.create(OpenTriviaAdapter::class.java)
    }

    override fun getResults(): Single<List<Result>> {
        //DO nothing
        return Single.just(arrayListOf())
    }

    override fun addResult(result: Result) {
        //do Nothing
    }

    override fun getQuestions(amount: Int): Single<List<Question>> {
        return openTriviaAdapter.getQuestions(amount, "multiple")
            .flatMap({
                val questions = mutableListOf<Question>()
                for (question in it.results) {
                    questions.add(Question(
                        question.question ?: "",
                        question.correct_answer ?: "",
                        question.incorrect_answers ?: arrayListOf())
                    )
                }

                Single.just(questions)
            })
    }


}