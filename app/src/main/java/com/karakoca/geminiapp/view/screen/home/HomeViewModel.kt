package com.karakoca.geminiapp.view.screen.home

import androidx.lifecycle.ViewModel
import com.karakoca.geminiapp.R
import com.karakoca.geminiapp.model.Constants
import com.karakoca.geminiapp.model.HomeContent
import com.karakoca.geminiapp.util.Screen

class HomeViewModel : ViewModel() {
    val homeContent = listOf<HomeContent>(
        HomeContent(R.string.chat, R.drawable.ic_chat, Screen.ChatScreen.route),
        HomeContent(R.string.dream_interpreter, R.drawable.ic_night_moon, Screen.ChatScreen.route+"?${Constants.CHAT_TYPE_COMMON}=false")
    )
}