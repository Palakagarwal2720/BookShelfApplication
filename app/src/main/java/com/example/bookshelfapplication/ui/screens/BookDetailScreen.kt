package com.example.bookshelfapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelfapplication.ui.dataClass.Book
import com.example.bookshelfapplication.ui.util.BookCard

@Composable
fun BookDetailScreen(book:Book,isFavorite:String) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()))
    {
        BookCard(book = book,isFavorite = isFavorite.toBoolean())
        Column(Modifier.padding(20.dp))
        {
            Text("Alias: ${book.alias}")
            Spacer(modifier = Modifier.height(10.dp))
            Text("Updated on: ${book.lastChapterDate}")
            Spacer(modifier = Modifier.height(30.dp))
            Text("Summary")
            Text("This is the book")
        }
    }

}