package com.he.sun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.he.sun.ui.theme.HeSun_COMP304_FinalTest_F24Theme
import androidx.compose.material3.OutlinedTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeSun_COMP304_FinalTest_F24Theme {
                Scaffold(modifier = Modifier.safeContentPadding()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
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

        }) { Text("Insert Stocks") }
        Button(onClick = {

        }) { Text("Display Stock Info") }


    }

}

