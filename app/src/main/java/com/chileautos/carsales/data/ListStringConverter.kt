package com.chileautos.carsales.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringConverter {

    var gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<String> {
        if (data == null) {
            return kotlin.collections.emptyList()
        }

        val listType = object : TypeToken<List<String>>(){}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toString(someObjects: List<String>): String {
        return gson.toJson(someObjects)
    }
}