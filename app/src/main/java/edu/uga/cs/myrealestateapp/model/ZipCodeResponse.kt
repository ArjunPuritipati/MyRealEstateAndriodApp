package edu.uga.cs.myrealestateapp.model

data class ZipCodeResponse(
    val places: List<Map<String, String>> = emptyList()
)
