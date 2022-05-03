package com.aplicada2.room.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aplicada2.room.data.database.dao.QuoteDao
import com.aplicada2.room.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase: RoomDatabase(){
    abstract fun getQuoteDao():QuoteDao
}