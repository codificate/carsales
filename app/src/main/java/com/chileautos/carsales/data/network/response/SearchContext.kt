package com.chileautos.carsales.data.network.response

data class SearchContext(
    val adSections: List<Any>,
    val canonicalQuery: String,
    val disclaimers: List<String>,
    val maxItemsPerPage: Int,
    val numOfItem: Int,
    val pageNum: Int,
    val predicate: String,
    val recordOffset: Int,
    val seoData: SeoData,
    val sortKey: String,
    val suggestedSavedSearchName: String,
    val totalPages: Int,
    val totalRecords: Int,
    val tracking: Tracking
)