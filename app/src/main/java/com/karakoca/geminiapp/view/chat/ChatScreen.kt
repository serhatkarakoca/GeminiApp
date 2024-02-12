package com.karakoca.geminiapp.view.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.karakoca.geminiapp.R
import com.karakoca.geminiapp.view.commonui.ShimmerContent

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    val prompt = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .verticalScroll(rememberScrollState())
        ) {
            AnimatedVisibility(visible = !state.isLoading) {
                Text(text = prompt.value, color = Color.Red)
                Divider(thickness = 1.dp, color = Color.Transparent)
                Text(modifier = Modifier.padding(16.dp), text = state.response?.text ?: "")
            }
            AnimatedVisibility(visible = state.isLoading, modifier = Modifier.padding(top = 8.dp)) {
                ShimmerContent()
            }
            Divider(thickness = 16.dp, color = Color.Transparent)


        }
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(8.dp)
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp),
                value = prompt.value,
                onValueChange = { newText ->
                    prompt.value = newText
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                maxLines = 6,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(size = 64.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                    ) {
                        if (prompt.value.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.input_placeholder),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .alpha(0.7f)
                                    .align(Alignment.CenterStart)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()
                            Icon(
                                modifier = Modifier
                                    .rotate(225f)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {

                                    },
                                painter = painterResource(id = R.drawable.ic_attachment),
                                contentDescription = null
                            )
                        }
                    }
                }
            )
            IconButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .align(Alignment.CenterVertically)
                    .alpha(if (prompt.value.isNotEmpty() && !state.isLoading) 1f else 0.5f)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
                enabled = prompt.value.isNotEmpty() && !state.isLoading,
                onClick = {
                    focusManager.clearFocus()
                    viewModel.sendMessage(prompt.value)
                    prompt.value = ""
                }) {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Send,
                    tint = MaterialTheme.colorScheme.surface,
                    contentDescription = null
                )
            }
        }

    }
}