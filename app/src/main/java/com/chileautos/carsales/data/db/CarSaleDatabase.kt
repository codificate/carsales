package com.chileautos.carsales.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chileautos.carsales.data.DataTypeConverter
import com.chileautos.carsales.data.ListStringConverter
import com.chileautos.carsales.data.db.entity.CarPost

@Database(entities = [CarPost::class], version = 1, exportSchema = true)
@TypeConverters(DataTypeConverter::class, ListStringConverter::class)
abstract class CarSaleDatabase : RoomDatabase() {

    abstract fun carPostDao(): CarPostDao

    companion object {
        @Volatile private var instance: CarSaleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    CarSaleDatabase::class.java, "carSaleEntries.db")
                    .build()
    }
}