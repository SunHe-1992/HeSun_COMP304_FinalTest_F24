package com.he.sun.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.he.sun.RoomDB.StockInfo

import kotlinx.coroutines.launch

class StockInfoViewModel(private val repository: AppRepository) : ViewModel() {

    var dbStockInfos by mutableStateOf<List<StockInfo>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            // Fetch initial city suggestions
            val fetchCities = repository.getStockInfosFromDB()
            dbStockInfos = fetchCities

        }
    }

    var weatherO by mutableStateOf<StockInfo?>(null)
        private set

    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
//                val weatherObj = repository.getCitiesFromDB(city)
//                if (weatherObj != null) {
//                    weatherO = weatherObj
//                }
            } catch (e: Exception) {
                Log.d("error", e.toString()) // Log any exceptions for debugging
            }
        }
    }


    fun insertStockInfo(stockSymbol: String, companyName: String, stockQuote: Double) {
        viewModelScope.launch {
            repository.insertStockInfo(StockInfo(stockSymbol, companyName, stockQuote))

            val dblist = repository.getStockInfosFromDB()
            dbStockInfos = dblist
        }
    }
}