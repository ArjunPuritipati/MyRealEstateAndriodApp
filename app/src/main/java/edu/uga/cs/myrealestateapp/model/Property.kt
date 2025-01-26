package edu.uga.cs.myrealestateapp.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status") val status: Status,
    @SerializedName("property") val property: List<Property>?
)

data class Status(
    val version: String? = null,
    val code: Int? = null,
    val msg: String? = null,
    val total: Int? = null,
    val page: Int? = null,
    val pagesize: Int? = null,
    val responseDateTime: String? = null,
    val transactionID: String? = null
)

data class Property(
    @SerializedName("identifier") val identifier: Identifier? = null,
    @SerializedName("lot") val lot: Lot? = null,
    @SerializedName("area") val area: Area? = null,
    @SerializedName("address") val address: Address? = null,
    @SerializedName("location") val location: Location? = null,
    @SerializedName("summary") val summary: Summary? = null,
    @SerializedName("utilities") val utilities: Utilities? = null,
    @SerializedName("building") val building: Building? = null,
    @SerializedName("vintage") val vintage: Vintage? = null
)

data class Identifier(
    @SerializedName("Id") val id: Long? = null,
    @SerializedName("attomId") val attomId: Long? = null
)

data class Lot(
    @SerializedName("lotnum") val lotNum: String? = null,
    @SerializedName("lotsize1") val lotSize: Double? = null,
    @SerializedName("lotsize2") val lotSizeSqFt: Int? = null,
    @SerializedName("pooltype") val poolType: String? = null,
    @SerializedName("frontage") val frontage: Int? = null // Additional field
)

data class Area(
    @SerializedName("loctype") val locType: String? = null,
    @SerializedName("countrysecsubd") val countrySubdivision: String? = null,
    @SerializedName("countyuse1") val countyUse: String? = null,
    @SerializedName("munname") val municipalityName: String? = null,
    @SerializedName("subdname") val subdivisionName: String? = null,
    @SerializedName("blockNum") val blockNum: String? = null, // Additional field
    @SerializedName("taxcodearea") val taxCodeArea: String? = null
)

data class Address(
    @SerializedName("oneLine") val oneLine: String? = null,
    @SerializedName("postal1") val postalCode: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("line1") val line1: String? = null,
    @SerializedName("line2") val line2: String? = null,
    @SerializedName("locality") val locality: String? = null
)

data class Location(
    @SerializedName("latitude") val latitude: String? = null,
    @SerializedName("longitude") val longitude: String? = null,
    @SerializedName("accuracy") val accuracy: String? = null,
    @SerializedName("distance") val distance: Double? = null // Additional field
)

data class Summary(
    @SerializedName("yearbuilt") val yearBuilt: Int? = null,
    @SerializedName("propertyType") val propertyType: String? = null,
    @SerializedName("propclass") val propertyClass: String? = null,
    @SerializedName("propsubtype") val propertySubType: String? = null,
    @SerializedName("absenteeInd") val absenteeIndicator: String? = null // Additional field
)

data class Utilities(
    @SerializedName("coolingtype") val coolingType: String? = null,
    @SerializedName("heatingtype") val heatingType: String? = null,
    @SerializedName("energyType") val energyType: String? = null,
    @SerializedName("sewertype") val sewerType: String? = null, // Additional field
    @SerializedName("waterType") val waterType: String? = null // Additional field
)

data class Building(
    @SerializedName("size") val size: BuildingSize? = null,
    @SerializedName("rooms") val rooms: Rooms? = null,
    @SerializedName("construction") val construction: Construction? = null, // Additional field
    @SerializedName("summary") val buildingSummary: BuildingSummary? = null
)

data class BuildingSize(
    @SerializedName("bldgsize") val buildingSize: Int? = null,
    @SerializedName("livingsize") val livingSize: Int? = null,
    @SerializedName("groundfloorsize") val groundFloorSize: Int? = null
)

data class Rooms(
    @SerializedName("beds") val beds: Int? = null,
    @SerializedName("bathsfull") val bathsFull: Int? = null,
    @SerializedName("bathstotal") val bathsTotal: Double? = null,
    @SerializedName("roomsTotal") val roomsTotal: Int? = null
)

data class Construction(
    @SerializedName("condition") val condition: String? = null,
    @SerializedName("roofcover") val roofCover: String? = null, // Additional field
    @SerializedName("wallType") val wallType: String? = null // Additional field
)

data class BuildingSummary(
    @SerializedName("archStyle") val architecturalStyle: String? = null,
    @SerializedName("levels") val levels: Int? = null
)

data class Vintage(
    @SerializedName("lastModified") val lastModified: String? = null,
    @SerializedName("pubDate") val pubDate: String? = null
)
