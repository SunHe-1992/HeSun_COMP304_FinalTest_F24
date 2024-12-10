package com.he.sun


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.he.sun.ui.theme.HeSun_COMP304_FinalTest_F24Theme
import kotlinx.coroutines.launch

class DisplayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val stockSymbol: String = intent.getStringExtra("stockSymbol").toString()
            val companyName: String = intent.getStringExtra("companyName").toString()
            val stockQuote: String = intent.getStringExtra("stockQuote").toString()
            HeSun_COMP304_FinalTest_F24Theme {
                Scaffold(modifier = Modifier.safeContentPadding()) { innerPadding ->
                    Greeting2(
                        stockSymbol,
                        companyName,
                        stockQuote,
                        modifier = Modifier.padding(innerPadding),
                        onFinish = { finish() },
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(
    stockSymbol: String,
    companyName: String,
    stockQuote: String,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit
) {
    Column() {
        Text(
            text = "stockSymbol is   $stockSymbol",
            modifier = modifier
        )
        Text(
            text = "companyName is   $companyName",
            modifier = modifier
        )
        Text(
            text = "stockQuote is   $stockQuote",
            modifier = modifier
        )

        Button(onClick = {
            onFinish()
        }) { Text("Back to MainActivity") }
    }
}

