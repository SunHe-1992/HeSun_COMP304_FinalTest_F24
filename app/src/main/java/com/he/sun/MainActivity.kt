package com.he.sun

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.he.sun.ui.theme.HeSun_COMP304_FinalTest_F24Theme
import androidx.lifecycle.ViewModelProvider
import com.he.sun.RoomDB.StockInfoDatabase
import com.he.sun.ViewModel.AppRepository
import com.he.sun.ViewModel.StockInfoViewModel
import com.he.sun.ViewModel.ViewModelFactory
import kotlinx.coroutines.launch

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
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
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
            var stockQuoteValue: Double? = stockQuote.toDoubleOrNull()
            if (stockQuoteValue != null) {
                if (!stockSymbol.isNullOrEmpty() && !companyName.isNullOrEmpty() && !stockQuote.isNullOrEmpty()) {
                    stockVM.insertStockInfo(
                        stockSymbol,
                        companyName,
                        stockQuoteValue
                    )

                }
            } else {
                stockQuote = "0"

            }
        }) { Text("Insert Stocks") }
        Button(onClick = {
            coroutineScope.launch { stockVM.refreshData() }
        }) { Text("Display Stock Info") }

        LazyColumn(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(stockVM.dbStockInfos.size) { index ->
                val item = stockVM.dbStockInfos[index]
                StockListRenderer(
                    item.stockSymbol,
                    item.companyName,
                    item.stockQuote.toString(),
                    index,
                    onDeleteClick = {
                        stockVM.deleteData(item)
                    },
                    onConfirmClick = {
                        val intent = Intent(
                            context,
                            DisplayActivity::class.java
                        )
                        intent.putExtra("stockSymbol", item.stockSymbol)
                        intent.putExtra("companyName", item.companyName)
                        intent.putExtra("stockQuote", item.stockQuote.toString())
                        context.startActivity(intent)
                    })
            }
        }
    }

}

@Composable
private fun StockListRenderer(
    stockName: String,
    companyName: String,
    stockQuote: String,
    index: Int,
    onDeleteClick: () -> Unit,
    onConfirmClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onConfirmClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stockName, style = MaterialTheme.typography.labelSmall)
                Text(
                    companyName, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    stockQuote, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Icon(
                modifier = Modifier.clickable { onDeleteClick() },
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete this info",
            )
        }
    }
}