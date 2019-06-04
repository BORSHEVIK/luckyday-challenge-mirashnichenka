package com.example.code_challenge.model

import java.io.Serializable

data class Result(val question: String, val ansver: String, val correctAnsver: String, val ansverResult: Boolean) : Serializable