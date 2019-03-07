package com.chileautos.carsales.data.network.response

data class Capability(
    val canSaveSearch: Boolean,
    val canShareSearch: Boolean,
    val haveDistanceFromPostcode: Boolean
)