package com.seferovich.vakatbav2.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seferovich.vakatbav2.ui.theme.Comfortaa

@Composable
fun VakatAndTime(vakat: String, time: String, modifier: Modifier = Modifier){
    Row (
        modifier = Modifier.width(240.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        ) {
        Text(
            text = vakat,
            fontFamily = Comfortaa,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp
        )
        Text(
            text = time,
            fontFamily = Comfortaa,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp
        )
    }
    Spacer(modifier = Modifier.padding(8.dp))
}
