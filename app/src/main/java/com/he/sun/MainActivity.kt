package com.he.sun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.he.sun.ui.theme.HeSun_COMP304_FinalTest_F24Theme
import androidx.lifecycle.ViewModelProvider
import com.he.sun.RoomDB.StockInfo
import com.he.sun.RoomDB.StockInfoDatabase
import com.he.sun.ViewModel.AppRepository
import com.he.sun.ViewModel.StockInfoViewModel
import com.he.sun.ViewModel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize database and ViewModels
        val database = StockInfoDatabase.getInstance(applicationContext)
        val repository = AppRepository(database.getStockInfoDAO())

        val vmFactory = ViewModelFactory(repository)
        val stockVM = ViewModelProvider(this, vmFactory)[StockInfoViewModel::class.java]



        setContent {
            HeSun_COMP304_FinalTest_F24Theme {
                Scaffold(modifier = Modifier.safeContentPadding()) { innerPadding ->
                    Greeting(

                        modifier = Modifier.padding(innerPadding),
                        stockVM
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, stockVM: StockInfoViewModel) {
    var stockSymbol by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var stockQuote by remember { mutableStateOf("") }
    Column() {

        Text(
            text = "Insert Stocks",
            modifier = modifier
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = stockSymbol,
            onValueChange = { stockSymbol = it },
            // Prompt text for the text field
            placeholder = { Text("Enter stock symbol") },
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = companyName,
            onValueChange = { companyName = it },
            // Prompt text for the text field
            placeholder = { Text("Enter company name") },
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = stockQuote,
            onValueChange = { stockQuote = it },
            // Prompt text for the text field
            placeholder = { Text("Enter stock quote") },
        )

        Button(onClick = {
            if (!stockSymbol.isNullOrEmpty() && !companyName.isNullOrEmpty() && !stockQuote.isNullOrEmpty()) {
                var stockQuoteValue: Double = stockQuote.toDouble()
                stockVM.insertStockInfo(
                    stockSymbol,
                    companyName,
                    stockQuoteValue
                )

            }
        }) { Text("Insert Stocks") }
        Button(onClick = {

        }) { Text("Display Stock Info") }


    }

}

