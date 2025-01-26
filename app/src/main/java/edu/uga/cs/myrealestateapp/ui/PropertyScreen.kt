package edu.uga.cs.myrealestateapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.viewmodel.PropertyViewModel

@Composable
fun PropertyScreen(
    viewModel: PropertyViewModel,
    onPropertyClick: (Long) -> Unit
) {
    val properties by viewModel.properties.collectAsState()
    val error by viewModel.error.collectAsState()

    // State for toggling search fields visibility
    var showSearchFields by remember { mutableStateOf(true) }

    var postalCode by remember { mutableStateOf("") }
    var radius by remember { mutableStateOf("50") }
    var minBeds by remember { mutableStateOf("1") }
    var maxBeds by remember { mutableStateOf("5") }
    var minBaths by remember { mutableStateOf("1") }
    var maxBaths by remember { mutableStateOf("5") }
    var selectedPropertyType by remember { mutableStateOf("All") }

    val propertyTypes = listOf(
        "All", "Apartment", "Single Family Residence", "Condominium", "Townhouse",
        "Multi-Family Home", "Mobile Home", "Commercial", "Vacant Land", "Industrial", "Farm", "Other"
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (showSearchFields) {
            // Search Fields
            Text(
                text = "Search Properties",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = postalCode,
                onValueChange = { postalCode = it },
                label = { Text("Postal Code") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = radius,
                onValueChange = { radius = it },
                label = { Text("Radius (miles)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = minBeds,
                    onValueChange = { minBeds = it },
                    label = { Text("Min Beds") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = maxBeds,
                    onValueChange = { maxBeds = it },
                    label = { Text("Max Beds") },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = minBaths,
                    onValueChange = { minBaths = it },
                    label = { Text("Min Baths") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = maxBaths,
                    onValueChange = { maxBaths = it },
                    label = { Text("Max Baths") },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            PropertyTypeDropdown(
                selectedType = selectedPropertyType,
                onTypeSelected = { selectedPropertyType = it },
                propertyTypes = propertyTypes
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Search Button
            Button(
                onClick = {
                    showSearchFields = false // Hide search fields
                    viewModel.fetchProperties(
                        postalCode = postalCode,
                        radius = radius.toIntOrNull() ?: 50,
                        minBeds = minBeds.toIntOrNull() ?: 1,
                        maxBeds = maxBeds.toIntOrNull() ?: 5,
                        minBaths = minBaths.toIntOrNull() ?: 1,
                        maxBaths = maxBaths.toIntOrNull() ?: 5,
                        propertyType = selectedPropertyType
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search Properties")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Display Error if exists
        if (error != null) {
            Text(text = "Error: $error", color = MaterialTheme.colorScheme.error)
        }

        // Display Property List
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(properties) { property ->
                PropertyItem(
                    property = property,
                    onClick = {
                        property.identifier?.attomId?.let { onPropertyClick(it) }
                    }
                )
            }
        }

        // Show "Back to Search" button if search fields are hidden
        if (!showSearchFields) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { showSearchFields = true }, // Show search fields again
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Search")
            }
        }
    }
}

@Composable
fun PropertyTypeDropdown(
    selectedType: String,
    onTypeSelected: (String) -> Unit,
    propertyTypes: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedType,
            onValueChange = {},
            label = { Text("Property Type") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            propertyTypes.forEach { type ->
                DropdownMenuItem(
                    onClick = {
                        onTypeSelected(type)
                        expanded = false
                    },
                    text = { Text(type) }
                )
            }
        }
    }
}

@Composable
fun PropertyItem(property: Property, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = property.address?.oneLine ?: "Unknown Address",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type: ${property.summary?.propertyType ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Bedrooms: ${property.building?.rooms?.beds ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Bathrooms: ${property.building?.rooms?.bathsTotal ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Year Built: ${property.summary?.yearBuilt ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Living Area: ${property.building?.size?.livingSize?.let { "$it sq ft" } ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Lot Size: ${property.lot?.lotSizeSqFt?.let { "$it sq ft" } ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
