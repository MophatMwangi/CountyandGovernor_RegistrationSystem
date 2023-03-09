package com.strath.countyandgovernor_registrationsystem.data

data class Lands(
    val acreage_uom: String,
    val active_status: Boolean,
    val arable_acres: Double,
    val climate: String,
    val duration: Double,
    val expiry_date: Long,
    val id: String,
    val latitude: String,
    val leaser: Any,
    val location: String,
    val longitude: String,
    val monthly_cost: Double,
    val name: String,
    val ownership: String,
    val photo_url: String,
    val remarks: String,
    val soil_type: String,
    val start_date: Long,
    val terrain: String,
    val total_acres: Double,
    val total_cost: Double,
    val unit_price: Double
)