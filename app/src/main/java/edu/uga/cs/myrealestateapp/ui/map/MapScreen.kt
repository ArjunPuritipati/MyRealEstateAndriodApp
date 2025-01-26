package edu.uga.cs.myrealestateapp.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import edu.uga.cs.myrealestateapp.viewmodel.MapViewModel

@Composable
fun MapScreen(mapViewModel: MapViewModel) {
    // Collect the state of properties from the ViewModel
    val properties by mapViewModel.properties.collectAsState(initial = emptyList())

    // Remember the camera position state for the map
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.builder()
            .target(LatLng(37.7749, -122.4194)) // Default position (San Francisco)
            .zoom(10f) // Default zoom level
            .build()
    }

    // Render the Google Map
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Loop through the properties and add markers
        properties.forEach { property ->
            val latitude = property.location?.latitude?.toDoubleOrNull()
            val longitude = property.location?.longitude?.toDoubleOrNull()

            if (latitude != null && longitude != null) {
                Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    title = property.address?.oneLine ?: "Unknown Address"
                )
            }
        }
    }
}
