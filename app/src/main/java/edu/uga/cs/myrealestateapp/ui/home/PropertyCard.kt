package edu.uga.cs.myrealestateapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.uga.cs.myrealestateapp.model.Property

@Composable
fun PropertyCard(property: Property) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Address: ${property.address?.oneLine ?: "N/A"}")
        Text(text = "Country: ${property.address?.country ?: "N/A"}")
        Text(text = "Price: ${property.summary?.propclass ?: "N/A"}")
        Text(text = "Building Size: ${property.building?.size?.bldgsize ?: "N/A"} sqft")
        Text(text = "Bedrooms: ${property.building?.rooms?.beds ?: "N/A"}")
        Text(text = "Bathrooms: ${property.building?.rooms?.bathsfull ?: "N/A"}")
        Text(text = "Year Built: ${property.summary?.yearbuilt ?: "N/A"}")
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyCardPreview() {
    val sampleProperty = Property(
        identifier = null,
        lot = null,
        area = null,
        address = edu.uga.cs.myrealestateapp.model.Address(
            country = "USA",
            oneLine = "123 Main St, Atlanta, GA"
        ),
        location = null,
        summary = edu.uga.cs.myrealestateapp.model.Summary(
            propclass = "Residential",
            yearbuilt = 1999
        ),
        utilities = null,
        building = edu.uga.cs.myrealestateapp.model.Building(
            size = edu.uga.cs.myrealestateapp.model.BuildingSize(
                bldgsize = 2000
            ),
            rooms = edu.uga.cs.myrealestateapp.model.Rooms(
                bathsfull = 2,
                beds = 3,
                roomsTotal = 5
            )
        ),
        vintage = null
    )

    PropertyCard(property = sampleProperty)
}
