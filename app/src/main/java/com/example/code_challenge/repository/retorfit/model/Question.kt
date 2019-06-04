package com.example.code_challenge.repository.retorfit.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class Question(
    @JsonProperty("category") var category: String?,
    @JsonProperty("type") var type: String?,
    @JsonProperty("difficulty") var difficulty: String?,
    @JsonProperty("question") var question: String?,
    @JsonProperty("correct_answer") var correct_answer: String?,
    @JsonProperty("incorrect_answers") var incorrect_answers: List<String>?
)