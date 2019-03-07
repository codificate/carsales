package com.chileautos.carsales.data.network.response

data class StockList(
    val capability: Capability,
    val result: List<Result>,
    val searchContext: SearchContext
)