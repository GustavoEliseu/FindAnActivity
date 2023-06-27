package com.example.findactivity.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.findactivity.data.model.ActEntity


@Database(entities = [ActEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ActDatabase : RoomDatabase() {
    abstract fun ActDAO(): ActDAO
}