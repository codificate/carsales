package com.chileautos.carsales.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<Any> {
        if (data == null) {
            return kotlin.collections.emptyList()
        }

        val listType = object : TypeToken<List<Any>>(){}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(someObjects: List<Any>): String {
        return gson.toJson(someObjects)
    }
}