package com.he.sun.ViewModel

import com.he.sun.RoomDB.StockInfoDAO
import com.he.sun.RoomDB.StockInfo


class AppRepository(private val StockInfoDAO: StockInfoDAO) {


    suspend fun getStockInfosFromDB(): List<StockInfo> {
        return StockInfoDAO.getAllStockInfo()
    }

    suspend fun insertStockInfo(c: StockInfo) {
        if (searchForStockInfoInDB(c.stockSymbol) == null) { // Avoid saving duplicate
            StockInfoDAO.insertStockInfo(c)
        } else {
            println("stockInfo is already in the database")
        }
    }


    suspend fun deleteStockInfo(c: StockInfo) {
        StockInfoDAO.deleteStockInfo(c)
    }


    suspend fun searchForStockInfoInDB(term: String): StockInfo? {

        return StockInfoDAO.getStockInfoBySymbol(term)
    }


    suspend fun update(newStockInfo: StockInfo) {
        StockInfoDAO.updateStockInfo(newStockInfo)
    }


    suspend fun deleteAllInfo() {
        StockInfoDAO.deleteAllInfo()
    }
}