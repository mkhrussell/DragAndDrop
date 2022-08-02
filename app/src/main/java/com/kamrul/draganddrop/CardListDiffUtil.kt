package com.kamrul.draganddrop

import androidx.recyclerview.widget.DiffUtil
import com.kamrul.draganddrop.model.CardModel

class CardListDiffUtil: DiffUtil.ItemCallback<CardModel>() {
    override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean =
        oldItem.label == newItem.label

    override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean =
        oldItem == newItem
}
