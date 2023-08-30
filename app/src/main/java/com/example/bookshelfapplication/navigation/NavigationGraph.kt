package com.example.bookshelfapplication.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookshelfapplication.ui.dataClass.Book
import com.example.bookshelfapplication.ui.screens.BookDetailScreen
import com.example.bookshelfapplication.ui.screens.BookShelfScreen
import com.example.bookshelfapplication.ui.screens.LoginScreen
import com.example.bookshelfapplication.ui.screens.SignUpScreen
import com.example.bookshelfapplication.ui.util.DataStoreUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun NavigationHost(navHostController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var startDestination = Routes.BOOK_SHELF_SCREEN




    BackHandler {
        if (navHostController.currentBackStack.value.size > 2) {
            navHostController.popBackStack()
        }
    }


    Box {
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            builder = navGraph(navHostController)
        )

        }
    }
fun navGraph(navHostController: NavHostController): NavGraphBuilder.() -> Unit {
    return {
        composable(route = Routes.BOOK_SHELF_SCREEN)
        {
            BookShelfScreen(navHostController)
        }
        composable(route = Routes.SIGN_UP_SCREEN)
        {
            SignUpScreen(navHostController)
        }
        composable(route = Routes.LOGIN_SCREEN)
        {
            LoginScreen(navHostController)
        }
        composable(route = "${Routes.BOOK_DETAILS_SCREEN}?book={book}&isFavorite={isFavorite}",
            arguments = listOf(navArgument("book") {
                type = NavType.StringType
                defaultValue = Gson().toJson(Book())
            }))
        {
            BookDetailScreen(book = Gson().fromJson(
                it.arguments?.getString("book"),
                Book::class.java
            ), isFavorite = it.arguments?.getString("isFavorite")?:"")
        }
    }
}