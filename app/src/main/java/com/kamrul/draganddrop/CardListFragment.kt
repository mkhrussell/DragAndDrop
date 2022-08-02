package com.kamrul.draganddrop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kamrul.draganddrop.databinding.FragmentCardlistBinding
import com.kamrul.draganddrop.model.CardModel

class CardListFragment : Fragment() {
    private lateinit var binding: FragmentCardlistBinding
    private lateinit var cardViewModel: CardListViewModel
    private lateinit var cardListAdapter: CardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupViewModelObservers()
        super.onViewCreated(view, savedInstanceState)
        cardViewModel.loadAllCards()
    }

    private fun setupRecyclerView() {
        cardListAdapter = CardListAdapter(adapterItemClick, onItemMove)
        binding.recyclerView.let {
        it.layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
            it.adapter = cardListAdapter
        }
    }

    private fun setupViewModelObservers() {
        cardViewModel = ViewModelProvider(this).get(CardListViewModel::class.java)
        cardViewModel.cards.observe(viewLifecycleOwner) {
            cardListAdapter.submitList(it)
        }
    }

    private val adapterItemClick: (launcherCard: CardModel) -> Unit = {
        Toast.makeText(requireContext(), "Clicked on: ${it.label}", Toast.LENGTH_SHORT).show()
    }

    private val onItemMove: (from: Int, to: Int) -> Unit = { from, to ->
        cardViewModel.move(from, to)
    }
}