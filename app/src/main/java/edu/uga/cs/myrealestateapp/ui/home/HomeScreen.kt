package edu.uga.cs.myrealestateapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
fun HomeScreen(viewModel: PropertyViewModel) {
    val properties by viewModel.properties.collectAsState()
    val error by viewModel.error.collectAsState()
    var selectedPropertyType by remember { mutableStateOf("All") } // State for dropdown filter

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Dropdown Filter
        Text(
            text = "Filter by Property Type",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        PropertyTypeDropdown(
            selectedType = selectedPropertyType,
            onTypeSelected = { selectedType ->
                selectedPropertyType = selectedType
                viewModel.filterPropertiesByType(selectedType) // Adjust ViewModel logic for filtering
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display Error if exists
        if (error != null) {
            Text(
                text = "Error: $error",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body1
            )
        } else {
            // Display Property List
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(properties) { property ->
                    PropertyItem(property = property)
                }
            }
        }
    }
}

@Composable
fun PropertyItem(property: Property) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = property.address?.oneLine ?: "Unknown Address",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Property Type: ${property.summary?.propertyType ?: "N/A"}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Bedrooms: ${property.building?.rooms?.beds ?: "N/A"}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Bathrooms: ${property.building?.rooms?.bathsTotal ?: "N/A"}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Year Built: ${property.summary?.yearBuilt ?: "N/A"}",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun PropertyTypeDropdown(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    val propertyTypes = listOf(
        "All",
        "Apartment",
        "Single Family Residence",
        "Condominium",
        "Townhouse",
        "Multi-Family Home",
        "Mobile Home",
        "Commercial",
        "Vacant Land",
        "Industrial",
        "Farm",
        "Other"
    )

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = selectedType,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Property Type") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Expand")
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            propertyTypes.forEach { label ->
                DropdownMenuItem(onClick = {
                    onTypeSelected(label)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}
