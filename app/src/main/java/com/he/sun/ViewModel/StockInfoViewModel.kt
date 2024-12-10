package com.he.sun.ViewModel

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

    suspend fun refreshData() {
        val fetchCities = repository.getStockInfosFromDB()
        dbStockInfos = fetchCities
    }


  fun deleteData(c: StockInfo){
      viewModelScope.launch {
          repository.deleteStockInfo(c)
          refreshData()
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