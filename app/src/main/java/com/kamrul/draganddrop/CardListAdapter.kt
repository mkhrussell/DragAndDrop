package com.kamrul.draganddrop

import android.content.ClipData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamrul.draganddrop.databinding.AdapterCardItemBinding
import com.kamrul.draganddrop.model.CardModel

class CardListAdapter(
    private val itemClick: (card: CardModel) -> Unit,
    private val itemMove: (from: Int, to: Int) -> Unit
) : ListAdapter<CardModel, CardListAdapter.ViewHolder>(CardListDiffUtil())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener,
        View.OnLongClickListener {

        private var appLabel: TextView = itemView.findViewById(R.id.tv_app_label)

        fun bind(position: Int) {
            val card = getItem(position)
            bindData(card)
            itemView.tag = position
            itemView.setOnClickListener(this@ViewHolder)
            itemView.setOnLongClickListener(this@ViewHolder)
            itemView.setOnDragListener(DragListener(itemMove))
        }

        private fun bindData(card: CardModel) {
            appLabel.text = card.label
            Log.d("CardListAdapter", "ViewHolder::bindData() ${appLabel.text}")
        }

        override fun onClick(view: View) {
            val card = getItem(adapterPosition)
            itemClick(card)
        }

        override fun onLongClick(view: View): Boolean {
            val card = getItem(adapterPosition)
            val data = ClipData.newPlainText("CARD_LABEL", card.label)
            val dragShadow = View.DragShadowBuilder(view)
            view.startDragAndDrop(data, dragShadow, view, 0)
            view.visibility = View.INVISIBLE

            val position = view.tag as Int
            Log.d("CardListAdapter", "onLongClick: view position = $position")

            return true
        }
    }
}
