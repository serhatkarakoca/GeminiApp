package com.karakoca.geminiapp

import com.google.ai.client.generativeai.GenerativeModel

object Gemini {
    val generativeModel = GenerativeModel(
        // For text-and-images input (multimodal), use the gemini-pro-vision model
        modelName = "gemini-pro", //gemini-pro-version
        // Access your API key as a Build Configuration variable (see "Set up your API key" above)
        apiKey = BuildConfig.apiKey,
    )
}