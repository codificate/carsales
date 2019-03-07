package com.chileautos.carsales.data.network.response

data class Ga(
    val eventKey: String,
    val eventType: String,
    val items: List<Item>,
    val memberTrackingId: String,
    val searchResults: SearchResults
)