package edu.uga.cs.myrealestateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import edu.uga.cs.myrealestateapp.ui.detail.DetailScreen
import edu.uga.cs.myrealestateapp.viewmodel.DetailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: DetailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        setContent {
            MyRealEstateApp(viewModel = viewModel)
        }
    }
}

@Composable
fun MyRealEstateApp(viewModel: DetailViewModel) {
    DetailScreen(viewModel = viewModel, attomId = "184713191")
}


