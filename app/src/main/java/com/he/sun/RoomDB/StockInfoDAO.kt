package com.he.sun.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface StockInfoDAO {

    @Query("select * from stock_info")
    suspend fun getAllStockInfo(): List<StockInfo>


    @Insert
    suspend fun insertStockInfo(c: StockInfo);


    @Delete
    suspend fun deleteStockInfo(c: StockInfo);


    @Update
    suspend fun updateStockInfo(info: StockInfo)


    @Query("DELETE FROM stock_info")
    suspend fun deleteAllInfo()


    @Query("SELECT * FROM stock_info WHERE stockSymbol = :stockSymbol")
    suspend fun getStockInfoBySymbol(stockSymbol: String): StockInfo?
}