package com.example.code_challenge.repository.room.entity

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.code_challenge.repository.room.dao.ResultDao
import com.example.code_challenge.repository.room.entity.CodeChalangeDatabase.Companion.VERSION
import com.example.code_challenge.repository.room.entity.Result

@Database(entities = arrayOf(Result::class), version = VERSION)
abstract class CodeChalangeDatabase : RoomDatabase() {

    companion object {
        const val VERSION = 1
        private const val DATABASE_NAME = "CodeChalange.db"

        private var INSTANCE: CodeChalangeDatabase? = null

        fun getInstance(context: Context): CodeChalangeDatabase {
            if (INSTANCE == null) {
                synchronized(CodeChalangeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CodeChalangeDatabase::class.java, DATABASE_NAME).build()
                }
            }

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun resultDao(): ResultDao


}