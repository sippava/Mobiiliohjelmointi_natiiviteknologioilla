package com.example.heartratelimits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.heartratelimits.ui.theme.HeartRateLimitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HeartRateLimitsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HeartRateLimits(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HeartRateLimits(modifier: Modifier = Modifier) {

    // Tallennetaan käyttäjän syöttämä ikä
    var ageInput by remember { mutableStateOf("") }

    // Muutetaan ikä numeroksi
    val age = ageInput.toIntOrNull() ?: 0

    // Lasketaan sykkeen ala- ja yläraja
    val lower = if (age > 0) (220 - age) * 0.65f else 0.0f
    val upper = if (age > 0) (220 - age) * 0.85f else 0.0f

    // Muutetaan tulokset kahden desimaalin tarkkuuteen joo
    val lowerFormatted = String.format("%.2f", lower)
    val upperFormatted = String.format("%.2f", upper)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = ageInput,
            onValueChange = { ageInput = it },
            label = { Text(text = stringResource(R.string.age)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(
                R.string.your_heart_rate_limits_are,
                lowerFormatted,
                upperFormatted
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}