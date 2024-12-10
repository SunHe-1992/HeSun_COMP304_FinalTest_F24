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

    /**
     * The current weather information for the selected city.
     */
    var weatherO by mutableStateOf<StockInfo?>(null)
        private set

    /**
     * Fetches weather information for a given city.
     *
     * @param city The name of the city to fetch weather data for.
     */
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
}