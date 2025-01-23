package edu.uga.cs.myrealestateapp.ui.detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.uga.cs.myrealestateapp.viewmodel.DetailViewModel

@Composable
fun DetailScreen(viewModel: DetailViewModel, attomId: String) {
    val property by viewModel.propertyDetails.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPropertyDetails(attomId)
    }

    property?.let {
        Log.d("DetailScreen", "Displaying property details: $it")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Address: ${it.address?.oneLine ?: "N/A"}",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Price: ${it.summary?.propclass ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Building Size: ${it.building?.size?.bldgsize ?: "N/A"} sqft",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Bedrooms: ${it.building?.rooms?.beds ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Bathrooms: ${it.building?.rooms?.bathsfull ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Year Built: ${it.summary?.yearbuilt ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } ?: run {
        Log.d("DetailScreen", "Loading property details or no data available")
        Text(text = "Loading property details...", modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(viewModel = DetailViewModel(), attomId = "184713191")
}
