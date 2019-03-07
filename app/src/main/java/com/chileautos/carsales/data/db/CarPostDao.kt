package com.chileautos.carsales.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chileautos.carsales.data.db.entity.CarPost

@Dao
interface CarPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkCarPostInsert(carPostList: List<CarPost>)

    @Query("select * from car_post")
    fun getAll(): LiveData<List<CarPost>>

    @Query("select * from car_post limit 1")
    fun getFirst(): LiveData<CarPost>
}