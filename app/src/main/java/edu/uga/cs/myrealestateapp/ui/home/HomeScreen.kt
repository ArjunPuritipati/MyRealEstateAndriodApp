package edu.uga.cs.myrealestateapp.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Example property
    val address = "123 Main St"
    val postalCode = "30303"

    // Navigate to detail screen when a property is clicked

    // navController.navigate("detail/$address/$postalCode")
}
