package com.example.booksapp.data.source.remote

import com.example.booksapp.common.Constants.Endpoint.GET_BOOKS
import com.example.booksapp.common.Constants.Endpoint.GET_BOOK_DETAIL
import com.example.booksapp.data.model.GetBookDetailResponse
import com.example.booksapp.data.model.GetBooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET(GET_BOOKS)
    fun getBooks(): Call<GetBooksResponse>

    @GET(GET_BOOK_DETAIL)
    fun getBookDetail(@Query("id") id: Int): Call<GetBookDetailResponse>

}