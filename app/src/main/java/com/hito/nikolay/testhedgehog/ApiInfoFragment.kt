package com.hito.nikolay.testhedgehog

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class ApiInfoFragment : Fragment() {

    private var webView: WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_api_info, container, false)
        activity?.setTitle(R.string.api_info)

        webView = view.findViewById(R.id.webView)
        webView?.let {
            it.settings.javaScriptEnabled = true
            it.webViewClient = WebViewClient()
            it.settings.domStorageEnabled = true
            it.loadUrl(getUrlApi())

            it.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && it.canGoBack()) {
                    it.goBack() // Navigate back to previous web page if there is one
                }
                true
            }
        }

        return view
    }


    private fun getUrlApi() = "https://www.icndb.com/api/"
}

