package com.jomeva.cco.activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jomeva.cco.R
import kotlinx.android.synthetic.main.activity_fids.*
import kotlinx.android.synthetic.main.activity_fids.webView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_view_banda_puente.view.*

class Fids : AppCompatActivity() {
    val URL_PRINCIPAL="http://fids-sksm.aerooriente.com.co/aiir/?page_id=122"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fids)
        webView.webChromeClient = object : WebChromeClient() {

        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                //swipe.isRefreshing=true

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

            }



        }
       webView.setInitialScale(100)
        val setting = webView.settings
        setting.javaScriptEnabled = true
        webView.loadUrl(URL_PRINCIPAL)
    }
}