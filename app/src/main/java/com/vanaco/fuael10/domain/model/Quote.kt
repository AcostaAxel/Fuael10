package com.vanaco.fuael10.domain.model

import com.vanaco.fuael10.data.database.entities.QuoteEntity
import com.vanaco.fuael10.data.model.QuoteModel

data class Quote(val quote:String, val diego:String)

fun QuoteModel.toDomain() = Quote(quote, diego)
fun QuoteEntity.toDomain() = Quote(quote, diego)
