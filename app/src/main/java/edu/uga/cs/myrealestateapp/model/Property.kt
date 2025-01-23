package edu.uga.cs.myrealestateapp.model

data class ApiResponse(
    val status: Status,
    val property: List<Property>?
)

data class Status(
    val version: String?,
    val code: Int?,
    val msg: String?,
    val total: Int?,
    val page: Int?,
    val pagesize: Int?,
    val responseDateTime: String?,
    val transactionID: String?
)

data class Property(
    val identifier: Identifier?,
    val lot: Lot?,
    val area: Area?,
    val address: Address?,
    val location: Location?,
    val summary: Summary?,
    val utilities: Utilities?,
    val building: Building?,
    val vintage: Vintage?
)

data class Identifier(val Id: Long?, val fips: String?, val apn: String?, val attomId: Long?)
data class Lot(val lotnum: String?, val lotsize1: Double?, val lotsize2: Int?, val pooltype: String?)
data class Area(val blockNum: String?, val loctype: String?, val muncode: String?, val munname: String?, val taxcodearea: String?)
data class Address(val country: String?, val oneLine: String?)
data class Location(val latitude: String?, val longitude: String?)
data class Summary(val propclass: String?, val yearbuilt: Int?)
data class Utilities(val heatingfuel: String?, val heatingtype: String?)
data class Building(val size: BuildingSize?, val rooms: Rooms?)
data class BuildingSize(val bldgsize: Int?)
data class Rooms(val bathsfull: Int?, val beds: Int?, val roomsTotal: Int?)
data class Vintage(val lastModified: String?)
