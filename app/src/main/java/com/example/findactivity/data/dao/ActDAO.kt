package com.example.findactivity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.findactivity.data.model.ActEntity
import io.reactivex.Single

@Dao
interface ActDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(boredList : List<ActEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveActivity(boredActivity : ActEntity)

    @Query("SELECT * FROM ACTIVITY_TABLE")
    fun getAllActivities(): Single<List<ActEntity>>

    @Query("SELECT * FROM ACTIVITY_TABLE WHERE id = :id")
    fun findActById(id: String): Single<ActEntity>

    @Query("SELECT * FROM ACTIVITY_TABLE WHERE `key` = :key")
    fun findActByKey(key: String): Single<ActEntity>

    @Query("SELECT * FROM ACTIVITY_TABLE WHERE `key` = :key")
    fun findIfActExistsByKey(key: String): Single<List<ActEntity>>
}