package edu.uga.cs.myrealestateapp.ui.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.viewmodel.DetailViewModel

@Composable
fun DetailScreen(propertyId: Long?, viewModel: DetailViewModel, onNavigateBack: () -> Unit) {
    Log.d("DetailScreen", "DetailScreen launched for propertyId: $propertyId")

    if (propertyId == null || propertyId <= 0) {
        Log.e("DetailScreen", "Invalid property ID: $propertyId. Cannot fetch details.")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Invalid Property ID. Please try again.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        return
    }

    // Trigger fetching property details
    LaunchedEffect(propertyId) {
        viewModel.fetchPropertyDetails(propertyId)
    }

    // Observe property details
    val property = viewModel.selectedProperty.collectAsState(initial = null).value

    if (property == null) {
        // Show loading message
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading property details...",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        // Show property details
        PropertyDetails(property = property, onNavigateBack = onNavigateBack)
    }
}

@Composable
fun PropertyDetails(property: Property, onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Back Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Property Details",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Address Section
        property.address?.let {
            Text(
                text = it.oneLine ?: "Unknown Address",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Property Summary
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Property Summary", style = MaterialTheme.typography.titleMedium)
                property.summary?.let {
                    DetailRow(label = "Year Built", value = it.yearBuilt?.toString() ?: "N/A")
                    DetailRow(label = "Property Type", value = it.propertyType ?: "N/A")
                    DetailRow(label = "Property Class", value = it.propertyClass ?: "N/A")
                    DetailRow(label = "Property Subtype", value = it.propertySubType ?: "N/A")
                }
            }
        }

        // Lot Information
        property.lot?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Lot Information", style = MaterialTheme.typography.titleMedium)
                    DetailRow(label = "Lot Number", value = it.lotNum ?: "N/A")
                    DetailRow(label = "Lot Size", value = "${it.lotSize ?: "N/A"} acres")
                    DetailRow(label = "Pool Type", value = it.poolType ?: "N/A")
                }
            }
        }

        // Building Details
        property.building?.let { building ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Building Details", style = MaterialTheme.typography.titleMedium)
                    building.size?.let {
                        DetailRow(
                            label = "Building Size",
                            value = "${it.buildingSize ?: "N/A"} sq ft"
                        )
                        DetailRow(
                            label = "Living Area",
                            value = "${it.livingSize ?: "N/A"} sq ft"
                        )
                    }
                    building.rooms?.let {
                        DetailRow(label = "Bedrooms", value = "${it.beds ?: "N/A"}")
                        DetailRow(label = "Bathrooms", value = "${it.bathsTotal ?: "N/A"}")
                        DetailRow(label = "Total Rooms", value = "${it.roomsTotal ?: "N/A"}")
                    }
                }
            }
        }

        // Utilities
        property.utilities?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Utilities", style = MaterialTheme.typography.titleMedium)
                    DetailRow(label = "Cooling Type", value = it.coolingType ?: "N/A")
                    DetailRow(label = "Heating Type", value = it.heatingType ?: "N/A")
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
