package com.jomeva.cco.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.jomeva.cco.R
import com.jomeva.cco.adapter.AdapterEquipoBandaPuente
import com.jomeva.cco.activities.modelo.Equipo
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    val URL_PRINCIPAL =
      "http://fids-sksm.aerooriente.com.co/aiir/wp-admin/edit.php?orderby=flight_f_date&order=asc&post_type=flights"
    //val URL_PRINCIPAL="https://google.com"
    lateinit var mlinearLayoutManager: LinearLayoutManager
    private var mListaEquipoRecycler: MutableList<Equipo> = arrayListOf()
    private lateinit var mAdapterEquipoBandaPuente: AdapterEquipoBandaPuente
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipe.isRefreshing=false
swipe.setOnRefreshListener {
    //swipe.setColorSchemeColors(Color.GREEN,Color.YELLOW,Color.RED)
    swipe.setBackgroundColor(Color.RED)
    webView.reload()
}
        fids.setOnClickListener{
            val intent=Intent(this,Fids::class.java)
            startActivity(intent)
        }
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }


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
                swipe.isRefreshing=true

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipe.isRefreshing=false
            }



        }

        val setting = webView.settings
        setting.javaScriptEnabled = true
        webView.loadUrl(URL_PRINCIPAL)

        //Recicler
//la lista
        mListaEquipoRecycler.add(Equipo("puente1", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=413"))
        mListaEquipoRecycler.add(Equipo("puente2", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=289"))
        mListaEquipoRecycler.add(Equipo("puente3", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=7"))
        mListaEquipoRecycler.add(Equipo("puente4", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=415"))
        mListaEquipoRecycler.add(Equipo("puente5", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=417"))
        mListaEquipoRecycler.add(Equipo("banda1", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=408"))
        mListaEquipoRecycler.add(Equipo("banda2", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=404"))
        mListaEquipoRecycler.add(Equipo("banda3", "http://fids-sksm.aerooriente.com.co/aiir/?page_id=297"))

        reciclerEquipo.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mAdapterEquipoBandaPuente = AdapterEquipoBandaPuente(this!!, mListaEquipoRecycler)
        reciclerEquipo.adapter = mAdapterEquipoBandaPuente


    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()

        } else {
            super.onBackPressed()
        }

    }
}