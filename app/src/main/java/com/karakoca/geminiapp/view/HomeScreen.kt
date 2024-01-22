package com.karakoca.geminiapp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.asImageOrNull
import com.karakoca.geminiapp.Gemini
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    var response by remember {
        mutableStateOf<GenerateContentResponse?>(null)
    }
    var image by remember {
        mutableStateOf<ImageBitmap?>(null)
    }

    val scope = rememberCoroutineScope()
    val prompt = remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            Text(text = prompt.value, color = Color.Red)
            TextField(value = prompt.value, onValueChange = {
                prompt.value = it
            })
            Divider(thickness = 1.dp, color = Color.Transparent)
            Text(text = response?.text ?: "")
            image?.let { Image(bitmap = it, contentDescription = null) }
            AnimatedVisibility(visible = response == null) {


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
            Divider(thickness = 16.dp, color = Color.Transparent)

            Button(onClick = {
                scope.launch {
                    response = null
                    response = Gemini.generativeModel.generateContent(prompt.value)
                    response?.candidates?.forEach { candidate ->
                        candidate.content.parts.forEach { part ->
                            println(part.toString())
                            image = part.asImageOrNull()?.asImageBitmap()
                        }
                    }
                }
            }) {
                Text(text = "Create")
            }

        }
    }
}