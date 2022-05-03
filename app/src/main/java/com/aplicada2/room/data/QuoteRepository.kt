package com.aplicada2.room.data

import com.aplicada2.room.data.database.dao.QuoteDao
import com.aplicada2.room.data.database.entities.QuoteEntity
import com.aplicada2.room.data.model.QuoteModel
import com.aplicada2.room.data.network.QuoteService
import com.aplicada2.room.domain.model.Quote
import com.aplicada2.room.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteDao: QuoteDao)
{


    suspend fun  getAllQuotesFromApi():List<Quote>{
        val response:List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}