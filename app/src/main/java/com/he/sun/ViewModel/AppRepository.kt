package com.he.sun.ViewModel

import com.he.sun.RoomDB.StockInfoDAO
import com.he.sun.RoomDB.StockInfo


/**
 * Repository class for handling data operations related to cities and weather.
 *
 * This class acts as a single source of truth for data, interacting with both the network APIs and the local database.
 */
class AppRepository(private val StockInfoDAO: StockInfoDAO) {


    /**
     * Retrieves all cities stored in the database.
     *
     * @return A list of all cities stored in the database.
     */
    suspend fun getCitiesFromDB(): List<StockInfo> {
        return StockInfoDAO.getAllCities()
    }

    /**
     * Inserts a new city into the database, avoiding duplicates.
     *
     * @param c The city to be inserted.
     */
//    suspend fun insertCity(c: StockInfo) {
//        if (searchCityNameInDB(c.stockSymbol) == null) { // Avoid saving duplicate cities
//            StockInfoDAO.insertCityToDB(c)
//        } else {
//            // Log that the city is already in the database
//            println("City is already in the database")
//        }
//    }

    /**
     * Deletes a city from the database.
     *
     * @param name The name of the city to be deleted.
     */
    suspend fun deleteCity(name: String) {
        //   StockInfoDAO.deleteCityByName(name)
    }

    /**
     * Searches for cities in the database based on a search term.
     *
     * @param term The search term to filter cities by.
     * @return A list of cities matching the search term.
     */
//    suspend fun searchForCityInDB(term: String): List<StockInfo> {
//        //   return StockInfoDAO.getCityNamed(term)
//    }

    /**
     * Searches for a city in the database by its exact name.
     *
     * @param term The exact name of the city to search for.
     * @return The city with the exact name, or null if not found.
     */
//    suspend fun searchCityNameInDB(term: String): StockInfo? {
//         return StockInfoDAO.getCityByExactName(term)
//    }

    /**
     * Updates an existing city in the database.
     *
     * @param newCity The updated city information.
     */
    suspend fun update(newStockInfo: StockInfo) {
        //    StockInfoDAO.updateCity(newStockInfo)
    }

    /**
     * Deletes all cities from the database.
     */
    suspend fun deleteAllCities() {
        StockInfoDAO.deleteAllCities()
    }
}