package com.vanaco.fuael10.domain

import com.vanaco.fuael10.data.QuoteRepository
import com.vanaco.fuael10.data.model.QuoteModel
import com.vanaco.fuael10.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): Quote?{
        val quotes = repository.getAllQuotesFromDatabase()
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }

}