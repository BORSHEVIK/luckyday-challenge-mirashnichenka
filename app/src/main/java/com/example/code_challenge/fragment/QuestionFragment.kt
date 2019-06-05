package com.example.code_challenge.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.code_challenge.R
import com.example.code_challenge.model.Question
import com.example.code_challenge.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class QuestionFragment : BaseFragment() {

    companion object {
        private const val QUESTION_AMOUNT = 1

        private const val BUNDLE_QUESTION = "BUNDLE_QUESTION"
    }

    private var recyclerView: View? = null

    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button

    private lateinit var answers: View
    private lateinit var questionText: TextView
    private var question: Question? = null

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.answer_fragment, container, false)

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)

        swipeRefreshLayout.setOnRefreshListener {
            abs?.getRepository()?.getQuestions(QUESTION_AMOUNT)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ result ->
                    swipeRefreshLayout.isRefreshing = false
                    state?.putSerializable(BUNDLE_QUESTION, result[0])
                    if (result != null) {
                        updateUI(result[0])
                    }
                },
                    {
                        swipeRefreshLayout.isRefreshing = false
                    })
        }

        buttonOne = view.findViewById(R.id.answer_1)
        buttonTwo = view.findViewById(R.id.answer_2)
        buttonThree = view.findViewById(R.id.answer_3)
        buttonFour = view.findViewById(R.id.answer_4)

        buttonOne.setOnClickListener {
            buttonOne.background = buttonOne.context.getDrawable(R.drawable.button_capsule_correct)
            updateDB(question!!.question, question!!.correctAnsver, question!!.correctAnsver, true)
        }
        buttonTwo.setOnClickListener {
            buttonOne.background = buttonOne.context.getDrawable(R.drawable.button_capsule_correct)
            buttonTwo.background = buttonTwo.context.getDrawable(R.drawable.button_capsule_incorrect)
            updateDB(question!!.question, question!!.correctAnsver, question!!.incorrectAnsvers[0], false)
        }
        buttonThree.setOnClickListener {
            buttonOne.background = buttonOne.context.getDrawable(R.drawable.button_capsule_correct)
            buttonThree.background = buttonThree.context.getDrawable(R.drawable.button_capsule_incorrect)
            updateDB(question!!.question, question!!.correctAnsver, question!!.incorrectAnsvers[1], false)
        }
        buttonFour.setOnClickListener {
            buttonOne.background = buttonOne.context.getDrawable(R.drawable.button_capsule_correct)
            buttonFour.background = buttonFour.context.getDrawable(R.drawable.button_capsule_incorrect)
            updateDB(question!!.question, question!!.correctAnsver, question!!.incorrectAnsvers[2], false)
        }

        questionText = view.findViewById(R.id.Question)

        if (question == null) {
            val result = state?.getSerializable(BUNDLE_QUESTION)
            question = if (result != null) result as Question else Question("", "", arrayListOf())
        }

        answers = view.findViewById(R.id.answers)

        return view
    }

    override fun onStop() {
        disposable?.dispose()
        super.onStop()
    }

    private fun updateUI(question: Question) {
        this.question = question

        answers.visibility = View.VISIBLE

        buttonOne.background = buttonOne.context.getDrawable(R.drawable.button_capsule)
        buttonTwo.background = buttonTwo.context.getDrawable(R.drawable.button_capsule)
        buttonThree.background = buttonThree.context.getDrawable(R.drawable.button_capsule)
        buttonFour.background = buttonFour.context.getDrawable(R.drawable.button_capsule)

        questionText.text = question.question

        if (question.incorrectAnsvers.isNotEmpty()) {
            buttonOne.text = question.correctAnsver
            buttonTwo.text = question.incorrectAnsvers[0]
            buttonThree.text = question.incorrectAnsvers[1]
            buttonFour.text = question.incorrectAnsvers[2]
        }
    }

    private fun updateDB(question: String, answer: String, correctAnswer: String, result: Boolean) {
        abs?.getRepository()?.addResult(Result(question, answer, correctAnswer, result))
    }

    override fun onDestroyView() {
        recyclerView = null
        super.onDestroyView()
    }
}