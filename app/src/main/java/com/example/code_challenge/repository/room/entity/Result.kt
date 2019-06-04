package com.example.code_challenge.repository.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.code_challenge.repository.room.entity.Result.Companion.TABLE_NAME
import java.io.Serializable

@Entity(tableName = TABLE_NAME)
data class Result(@PrimaryKey(autoGenerate = true) var id: Long?,
                  @ColumnInfo(name = "question") var question: String,
                  @ColumnInfo(name = "ansver") var ansver: String,
                  @ColumnInfo(name = "correctAnsver") var correctAnsver: String,
                  @ColumnInfo(name = "ansverResult") var ansverResult: Boolean) : Serializable {



    constructor():this(null, "", "", "", false)

    companion object {

        const val TABLE_NAME = "Person"
    }

}