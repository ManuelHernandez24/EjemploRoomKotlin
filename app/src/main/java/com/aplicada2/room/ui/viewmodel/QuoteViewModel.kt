package com.aplicada2.room.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplicada2.room.data.model.QuoteModel
import com.aplicada2.room.domain.GetQuotesUseCase
import com.aplicada2.room.domain.GetRandomQuoteUseCase
import com.aplicada2.room.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase:GetQuotesUseCase,
    private val getRandomQuoteUseCase:GetRandomQuoteUseCase

) : ViewModel() {

    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result:List<Quote>? = getQuotesUseCase()

            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote(){
        viewModelScope.launch {
            isLoading.postValue(true)
            isLoading.postValue(false)
            val quote = getRandomQuoteUseCase()
            if(quote!=null){
                quoteModel.postValue(quote)
            }
        }


        //val currenQuote: QuoteModel = QuoteProvider.random()
        //quoteModel.postValue(currenQuote)


    }


}