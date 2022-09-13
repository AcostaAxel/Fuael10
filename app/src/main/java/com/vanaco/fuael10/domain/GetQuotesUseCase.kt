package com.vanaco.fuael10.domain

import com.vanaco.fuael10.data.QuoteRepository
import com.vanaco.fuael10.data.database.entities.toDatabase
import com.vanaco.fuael10.data.model.QuoteModel
import com.vanaco.fuael10.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository){

    suspend operator fun invoke():List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }


    }

}