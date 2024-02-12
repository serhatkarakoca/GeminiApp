package com.karakoca.geminiapp.view.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import com.karakoca.geminiapp.Gemini
import com.karakoca.geminiapp.model.Chat
import com.karakoca.geminiapp.model.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class ChatViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val chatHistory: ArrayList<Chat> = arrayListOf()

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state
    private lateinit var chat: com.google.ai.client.generativeai.Chat
    private val dreamPrompt =
        "Sen bir rüya yorumlayıcısısın. Rüya haricinde yorum yapmanı istemiyorum. Ben hangi dilde yazarsam sende o dilde cevap ver"
    private val commonPrompt = "Merhaba, ben hangi dilde yazarsam sende o dilde cevap ver"

    init {
        val isCommonChat = savedStateHandle.get<Boolean>(Constants.CHAT_TYPE_COMMON)
        initChat(isCommonChat ?: true) // CHAT_TYPE_COMMON 'true' ise genel 'false' ise rüya yorumcusu modu aktif olur.
    }

    private fun initChat(isCommonChat: Boolean) {
        val chat1 = Chat(
            "user",
            if (isCommonChat) commonPrompt else dreamPrompt
        )
        val chat2 = Chat("model", "Elbette")
        chatHistory.add(chat1)
        chatHistory.add(chat2)
        chat = Gemini.generativeModel.startChat(convertToContent())
        sendMessage("Merhaba ne konuda sorular sorabilirim")
    }

    fun sendMessage(message: String) {
        val userPrompt = Chat("user", message)
        chatHistory.add(userPrompt)
        setState(_state.value.copy(response = null, isLoading = true))
        viewModelScope.launch {
            val response = chat.sendMessage(message)
            chatHistory.add(Chat("model", response.text ?: ""))
            setState(_state.value.copy(response = response, isLoading = false))
        }
    }

    private fun convertToContent(): List<Content> {
        return chatHistory.map { content(it.role) { text(it.message) } }
    }


    private fun setState(state: HomeState) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }

    data class HomeState(
        val response: GenerateContentResponse? = null,
        val isLoading: Boolean = false
    )

}