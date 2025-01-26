package edu.uga.cs.myrealestateapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.uga.cs.myrealestateapp.repository.PropertyRepository
import edu.uga.cs.myrealestateapp.viewmodel.MapViewModel

class ViewModelFactory(private val repository: PropertyRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MapViewModel::class.java) -> MapViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
