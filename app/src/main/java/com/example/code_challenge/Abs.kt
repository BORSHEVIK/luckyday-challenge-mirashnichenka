package com.example.code_challenge

import com.example.code_challenge.repository.Repository

interface Abs {
    fun getRepository(): Repository
}