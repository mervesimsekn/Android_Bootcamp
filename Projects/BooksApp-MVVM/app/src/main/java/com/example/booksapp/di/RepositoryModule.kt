package com.example.booksapp.di

import com.example.booksapp.data.repository.BooksRepository
import com.example.booksapp.data.source.remote.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideBooksRepository(bookService: BookService) : BooksRepository = BooksRepository(bookService)
}