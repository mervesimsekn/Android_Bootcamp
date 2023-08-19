package com.example.booksapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.booksapp.MainApplication
import com.example.booksapp.R
import com.example.booksapp.common.viewBinding
import com.example.booksapp.data.model.GetBooksResponse
import com.example.booksapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home), BooksAdapter.BookListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val booksAdapter by lazy { BooksAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.adapter = booksAdapter
        getBooks()

    }

    private fun getBooks() {
        MainApplication.bookService?.getBooks()?.enqueue(object : Callback<GetBooksResponse> {
            override fun onResponse(
                call: Call<GetBooksResponse>,
                response: Response<GetBooksResponse>
            ) {
                val result = response.body()?.books

                if (result.isNullOrEmpty().not()) {
                    booksAdapter.submitList(result)
                }
            }

            override fun onFailure(call: Call<GetBooksResponse>, t: Throwable) {
                Log.e("GetBooks:", t.message.orEmpty())
            }

        })
    }

    override fun onBookClick(id: Int) {
        val action = HomeFragmentDirections.homeToDetail(id)
        findNavController().navigate(action)
    }

}