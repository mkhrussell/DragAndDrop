package com.kamrul.draganddrop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamrul.draganddrop.model.CardModel

class CardListViewModel : ViewModel() {
    private val _cards = MutableLiveData<List<CardModel>>()
    val cards: LiveData<List<CardModel>> = _cards

    fun loadAllCards() {
        val appList = mutableListOf<CardModel>()
        for (num in 1..100) {
            val card = CardModel("App #$num")
            appList.add(card)
        }
        _cards.value = appList
    }

    fun move(from: Int, to: Int) {
        _cards.value?.let {
            val currentCards = it.toMutableList()
            val card = currentCards.removeAt(from)
            currentCards.add(to, card)
            _cards.value = currentCards
        }
    }
}
