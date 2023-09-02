package com.example.booksapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.common.viewBinding
import com.example.booksapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), BooksAdapter.BookListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by viewModels<HomeViewModel>()

    private val booksAdapter by lazy { BooksAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.adapter = booksAdapter

        viewModel.getBooks()

        observeData()
    }

    private fun observeData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        viewModel.booksLiveData.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                booksAdapter.submitList(list)
            } else {
                Snackbar.make(requireView(), "Empty List!", 1000).show()
            }
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, 1000).show()
        }
    }

    override fun onBookClick(id: Int) {
        val action = HomeFragmentDirections.homeToDetail(id)
        findNavController().navigate(action)
    }

}