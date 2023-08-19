package com.example.booksapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.booksapp.MainApplication
import com.example.booksapp.R
import com.example.booksapp.common.viewBinding
import com.example.booksapp.data.model.GetBookDetailResponse
import com.example.booksapp.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBookDetail(args.id)

    }

    private fun getBookDetail(id: Int) {
        MainApplication.bookService?.getBookDetail(id)
            ?.enqueue(object : Callback<GetBookDetailResponse> {
                override fun onResponse(
                    call: Call<GetBookDetailResponse>, response: Response<GetBookDetailResponse>
                ) {
                    val result = response.body()?.book

                    if (result != null) {
                        with(binding) {
                            tvTitle.text = result.name
                            tvPrice.text = "${result.price} ₺"

                            Glide.with(ivCover.context).load(result.imageUrl).into(ivCover)

                            tvAuthor.text = "Author: ${result.author}"
                            tvPublisher.text = "Publisher: ${result.publisher}"

                        }
                    }
                }

                override fun onFailure(call: Call<GetBookDetailResponse>, t: Throwable) {
                    Log.e("GetBooks:", t.message.orEmpty())
                }

            })
    }

}