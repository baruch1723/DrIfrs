package com.example.drifrs // Replace with your package name

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webview)
        val webSettings: WebSettings = webView.settings

        // --- START: Enhanced WebView Configuration ---

        // Enable JavaScript - required for many modern web pages
        webSettings.javaScriptEnabled = true

        // Set a common desktop user agent to avoid being blocked
        webSettings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36"

        // Enable DOM Storage - essential for modern web apps that use localStorage
        webSettings.domStorageEnabled = true

        // Enable Database Storage
        webSettings.databaseEnabled = true

        // Tell the WebView to load from the network and not from the cache
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE

        // Set clients to handle page navigation and browser UI
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        // --- END: Enhanced WebView Configuration ---

        // Load the chat URL
        val chatUrl = "https://chatwith.tools/embed/1e1ec1da-0f28-4e9d-ad6d-7adae0a7d98f"
        webView.loadUrl(chatUrl)
    }
}
