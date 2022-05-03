package com.aplicada2.room.domain.model

import com.aplicada2.room.data.database.entities.QuoteEntity
import com.aplicada2.room.data.model.QuoteModel

data class Quote(val quote:String, val author:String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)