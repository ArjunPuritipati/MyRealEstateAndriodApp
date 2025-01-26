package edu.uga.cs.myrealestateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.uga.cs.myrealestateapp.repository.PropertyRepository
import edu.uga.cs.myrealestateapp.ui.PropertyScreen
import edu.uga.cs.myrealestateapp.ui.detail.DetailScreen
import edu.uga.cs.myrealestateapp.ui.theme.MyRealEstateAppTheme
import edu.uga.cs.myrealestateapp.viewmodel.DetailViewModel
import edu.uga.cs.myrealestateapp.viewmodel.DetailViewModelFactory
import edu.uga.cs.myrealestateapp.viewmodel.PropertyViewModel
import edu.uga.cs.myrealestateapp.viewmodel.PropertyViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize repository
        val repository = PropertyRepository()

        // Create PropertyViewModel using factory
        val propertyViewModelFactory = PropertyViewModelFactory(repository)
        val propertyViewModel = ViewModelProvider(this, propertyViewModelFactory)
            .get(PropertyViewModel::class.java)

        // Create DetailViewModel using factory
        val detailViewModelFactory = DetailViewModelFactory(repository)
        val detailViewModel = ViewModelProvider(this, detailViewModelFactory)
            .get(DetailViewModel::class.java)

        setContent {
            MyRealEstateAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "property_screen") {
                    composable("property_screen") {
                        PropertyScreen(
                            viewModel = propertyViewModel,
                            onPropertyClick = { propertyId ->
                                navController.navigate("detail_screen/$propertyId")
                            }
                        )
                    }
                    composable(
                        route = "detail_screen/{propertyId}",
                        arguments = listOf(navArgument("propertyId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val propertyId = backStackEntry.arguments?.getLong("propertyId") ?: -1L
                        DetailScreen(
                            propertyId = propertyId,
                            viewModel = detailViewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
