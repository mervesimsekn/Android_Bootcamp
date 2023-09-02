package com.example.booksapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.booksapp.MainApplication
import com.example.booksapp.R
import com.example.booksapp.common.viewBinding
import com.example.booksapp.data.model.GetBookDetailResponse
import com.example.booksapp.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBookDetail(args.id)

        observeData()
    }

    private fun observeData() = with(binding) {

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        viewModel.bookDetailLiveData.observe(viewLifecycleOwner) { book ->
            if (book != null) {

                tvTitle.text = book.name
                tvPrice.text = "${book.price} ₺"

                com.bumptech.glide.Glide.with(ivCover.context).load(book.imageUrl)
                    .into(ivCover)

                tvAuthor.text = "Author: ${book.author}"
                tvPublisher.text = "Publisher: ${book.publisher}"
            } else {
                Snackbar.make(requireView(), "Book Not Found!", 1000).show()
            }
        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, 1000).show()
        }
    }
}