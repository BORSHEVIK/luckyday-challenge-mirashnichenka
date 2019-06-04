package com.example.code_challenge.repository.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.code_challenge.repository.room.entity.Result
import io.reactivex.Single

@Dao
interface ResultDao {

    @Query("SELECT * from " + Result.TABLE_NAME)
    fun getAll(): Single<List<Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Result)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(persons: List<Result>)

    @Query("DELETE from " + Result.TABLE_NAME)
    fun deleteAll()

}