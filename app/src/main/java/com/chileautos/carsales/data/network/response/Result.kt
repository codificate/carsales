package com.chileautos.carsales.data.network.response

data class Result(
    val displayLocation: String,
    val displayPrice: DisplayPrice,
    val displayTitle: String,
    val keyDetails: List<String>,
    val mainPhoto: String,
    val manufacturerCertifications: List<Any>,
    val networkId: String,
    val photos: List<String>,
    val siloTypeColour: String,
    val siloTypeFriendlyName: String,
    val webDetailsUrl: String
)