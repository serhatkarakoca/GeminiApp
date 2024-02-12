package com.karakoca.geminiapp.view.commonui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(.8f)
                .height(20.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(1.dp))
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(.6f)
                .height(20.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(1.dp))
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(.8f)
                .height(20.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(1.dp))
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(.6f)
                .height(20.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(1.dp))
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(.8f)
                .height(20.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(1.dp))
        )
    }
}