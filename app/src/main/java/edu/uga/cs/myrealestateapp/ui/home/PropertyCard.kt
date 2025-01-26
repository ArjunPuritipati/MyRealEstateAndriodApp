package edu.uga.cs.myrealestateapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.uga.cs.myrealestateapp.model.Address
import edu.uga.cs.myrealestateapp.model.Area
import edu.uga.cs.myrealestateapp.model.Building
import edu.uga.cs.myrealestateapp.model.BuildingSize
import edu.uga.cs.myrealestateapp.model.BuildingSummary
import edu.uga.cs.myrealestateapp.model.Identifier
import edu.uga.cs.myrealestateapp.model.Location
import edu.uga.cs.myrealestateapp.model.Lot
import edu.uga.cs.myrealestateapp.model.Property
import edu.uga.cs.myrealestateapp.model.Rooms
import edu.uga.cs.myrealestateapp.model.Summary
import edu.uga.cs.myrealestateapp.model.Utilities
import edu.uga.cs.myrealestateapp.model.Vintage

@Composable
fun PropertyCard(property: Property) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = property.address?.oneLine ?: "Address not available",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Building Size: ${property.building?.size?.livingSize ?: "Unknown"} sq ft",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Lot Size: ${property.lot?.lotSize ?: "Unknown"} acres",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyCardPreview() {
    PropertyCard(
        property = Property(
            identifier = Identifier(id = 123456, attomId = 654321),
            lot = Lot(lotNum = "17", lotSize = 0.5, lotSizeSqFt = 21780, poolType = "None"),
            area = Area(
                locType = "Residential",
                countrySubdivision = "Clarke",
                countyUse = "001",
                municipalityName = "Athens",
                subdivisionName = "Smokey Hills",
                taxCodeArea = "1"
            ),
            address = Address(
                oneLine = "346 NOKETCHEE DR, ATHENS, GA 30601",
                postalCode = "30601",
                line1 = "346 NOKETCHEE DR",
                line2 = "Athens, GA 30601",
                locality = "Athens",
                country = "US"
            ),
            location = Location(
                latitude = "34.012140",
                longitude = "-83.357357",
                accuracy = "Rooftop"
            ),
            summary = Summary(
                yearBuilt = 1999,
                propertyType = "Single Family",
                propertyClass = "Residential",
                propertySubType = "SFR"
            ),
            utilities = Utilities(
                coolingType = "Central",
                heatingType = "Heat Pump"
            ),
            building = Building(
                size = BuildingSize(
                    buildingSize = 1356,
                    livingSize = 1356,
                    groundFloorSize = 1356
                ),
                rooms = Rooms(
                    beds = 3,
                    bathsFull = 2,
                    bathsTotal = 2.0,
                    roomsTotal = 6
                ),
                buildingSummary = BuildingSummary(
                    architecturalStyle = "Other",
                    levels = 1
                )
            ),
            vintage = Vintage(
                lastModified = "2024-12-24",
                pubDate = "2024-12-24"
            )
        )
    )
}
