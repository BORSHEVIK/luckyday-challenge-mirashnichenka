package com.example.code_challenge.repository.retorfit.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class GetQuestionsResponse(
    @JsonProperty("response_code") var responseCode: Int?,
    @JsonProperty("results") var results: List<Question>
)