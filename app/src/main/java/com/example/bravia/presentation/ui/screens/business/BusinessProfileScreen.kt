package com.example.bravia.presentation.ui.screens.business

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.ui.layout.MainLayout
import com.example.bravia.presentation.viewmodel.BusinessViewModel

@Composable
fun BusinessProfileScreen(
    navController: NavController,
    businessViewModel: BusinessViewModel,
    paddingValues: PaddingValues
) {

    MainLayout(paddingValues = paddingValues) {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           item {
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(180.dp)
               ) {

               }
           }
        }
    }
}