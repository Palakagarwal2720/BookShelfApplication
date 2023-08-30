package com.example.bookshelfapplication.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.bookshelfapplication.R
import com.example.bookshelfapplication.ui.dataClass.Book

@Composable
fun BookCard(book: Book, isFavorite:Boolean = false, onChange:()->Unit={},modifier:Modifier = Modifier)
{
    Card(modifier = Modifier
        .padding(20.dp)
        .background(Color.White).then(modifier))
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Row(Modifier.weight(2f))
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(book.image)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null,
                )
                Row(Modifier.padding(start = 10.dp))
                {
                    Column()
                    {
                        if (book.title?.isNotEmpty() == true) {
                            Text(text = "${book.title}", maxLines = 1)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "Hits : ${book.hits}")
                    }
                }
            }
            Image(
                modifier = Modifier.weight(0.5f).clickable {
                    onChange()
                },
                painter = painterResource(id = if (!isFavorite) R.drawable.favorite_icon else R.drawable.baseline_favorite_24),
                contentDescription = ""
            )

        }
    }

}