package com.he.sun.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface StockInfoDAO {
    /**
     * Retrieves all cities from the database.
     *
     * @return A list of all cities.
     */
    @Query("select * from stock_info")
    suspend fun getAllCities(): List<StockInfo>

    /**
     * Inserts a new city into the database.
     *
     * @param c The city to be inserted.
     */
    @Insert
    suspend fun insertCityToDB(c: StockInfo);

    /**
     * Deletes a city from the database.
     *
     * @param c The city to be deleted.
     */
    @Delete
    suspend fun deleteCity(c: StockInfo);


    /**
     * Updates an existing city in the database.
     *
     * @param CityToUpdate The city with updated information.
     */
    @Update
    suspend fun updateCity(CityToUpdate: StockInfo)

    /**
     * Deletes all cities from the database.
     */
    @Query("DELETE FROM stock_info")
    suspend fun deleteAllCities()


}