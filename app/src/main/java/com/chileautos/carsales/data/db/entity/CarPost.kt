package com.chileautos.carsales.data.db.entity

import androidx.room.*
import com.chileautos.carsales.data.DataTypeConverter
import com.chileautos.carsales.data.ListStringConverter

@Entity(tableName = "car_post", indices = [Index(value = ["networkId"], unique = true)])
data class CarPost(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val networkId: String,
    @Embedded
    val displayPrice: DisplayPrice,
    val displayLocation: String,
    val displayTitle: String,
    @TypeConverters(ListStringConverter::class)
    val keyDetails: List<String> ,
    val mainPhoto: String,
    @TypeConverters(DataTypeConverter::class)
    val manufacturerCertifications: List<Any>,
    @TypeConverters(ListStringConverter::class)
    val photos: List<String>,
    val siloTypeColour: String,
    val siloTypeFriendlyName: String,
    val webDetailsUrl: String
)