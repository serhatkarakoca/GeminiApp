package com.karakoca.geminiapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import com.karakoca.geminiapp.Gemini
import com.karakoca.geminiapp.R
import com.karakoca.geminiapp.model.Chat
import com.karakoca.geminiapp.model.Constants
import com.karakoca.geminiapp.model.HomeContent
import com.karakoca.geminiapp.util.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val homeContent = listOf<HomeContent>(
        HomeContent(R.string.chat, R.drawable.ic_chat, Screen.ChatScreen.route),
        HomeContent(R.string.dream_interpreter, R.drawable.ic_night_moon, Screen.ChatScreen.route+"?${Constants.CHAT_TYPE_COMMON}=false")
    )
}