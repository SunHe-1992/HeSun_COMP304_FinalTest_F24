package com.he.sun.RoomDB

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Represents the database for storing city information.
 */
@androidx.room.Database(entities = [StockInfo::class], version = 1)
abstract class StockInfoDatabase : RoomDatabase() {


    abstract fun getStockInfoDAO(): StockInfoDAO


    companion object {
        @Volatile
        private var INSTANCE: StockInfoDatabase? = null


        fun getInstance(context: Context): StockInfoDatabase {
            synchronized(this) {
                var inst = INSTANCE
                if (inst == null) {
                    inst = Room.databaseBuilder(
                        context,
                        StockInfoDatabase::class.java,
                        "stocksDB"
                    ).build()
                }
                INSTANCE = inst
                return inst
            }
        }
    }
}