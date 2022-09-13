package com.vanaco.fuael10.data

import com.vanaco.fuael10.data.database.dao.QuoteDao
import com.vanaco.fuael10.data.database.entities.QuoteEntity
import com.vanaco.fuael10.data.network.QuoteService
import com.vanaco.fuael10.domain.model.Quote
import com.vanaco.fuael10.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}