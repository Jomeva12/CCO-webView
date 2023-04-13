package com.jomeva.cco.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.jomeva.cco.R
import com.jomeva.cco.activities.modelo.Equipo
import kotlinx.android.synthetic.main.card_view_banda_puente.view.*

class AdapterEquipoBandaPuente : RecyclerView.Adapter<AdapterEquipoBandaPuente.ViewHolder> {

    private var modelo: MutableList<Equipo>
    private var inflater: LayoutInflater
    private var context: Context? = null
    private var ultimoId:String=""

    constructor(context: Context, modelo: MutableList<Equipo>) : super() {
        this.modelo = modelo
        this.inflater = LayoutInflater.from(context)
        this.context = context

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.itemView.webViewEquipo.webChromeClient= object : WebChromeClient(){

        }
        holder.itemView.webViewEquipo.webViewClient=object : WebViewClient(){


        }
        //holder.itemView.webViewEquipo.settings.useWideViewPort = true
        //holder.itemView.webViewEquipo.settings.loadWithOverviewMode = true
        holder.itemView.webViewEquipo.setInitialScale(100)




        val setting=   holder.itemView.webViewEquipo.settings
        setting.javaScriptEnabled=true
        holder.itemView.webViewEquipo.loadUrl(modelo[position].url)


    }
    public class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = inflater.inflate(R.layout.card_view_banda_puente, parent, false)

        return ViewHolder(vista)
    }


    override fun getItemCount(): Int {
        var tam = modelo.size

        return tam
    }
}