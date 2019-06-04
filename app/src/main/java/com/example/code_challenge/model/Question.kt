package com.example.code_challenge.model

import java.io.Serializable

data class Question(val question: String, val correctAnsver: String, val incorrectAnsvers: List<String>) : Serializable