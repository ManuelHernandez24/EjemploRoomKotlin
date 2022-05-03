package com.aplicada2.room.domain

import com.aplicada2.room.data.QuoteRepository
import com.aplicada2.room.data.database.entities.toDatabase
import com.aplicada2.room.data.model.QuoteModel
import com.aplicada2.room.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository) {

    /*suspend operator fun invoke():List<QuoteModel>?{
        return repository.getAllQuotes()
    }*/

    suspend operator fun invoke():List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            //En caso de que no haya un error con el servidor
                repository.clearQuotes()
            repository.insertQuotes(quotes.map {it.toDatabase()})
            quotes
        }else{
            //En caso de que haya un error con el servidor
            repository.getAllQuotesFromDatabase()
        }
    }

}