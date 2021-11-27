package com.example.programacionmovilciclo4

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var lugares: ArrayList<LugaresSanAndres> = ArrayList()
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        recycler = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)

        recycler.layoutManager = linearLayoutManager
        initDataFromFile()

    }

    private fun initDataFromFile() {
        try {
            val obj = JSONObject(Utils(this).loadJsonFromAssets(applicationContext, "POI.json"))
            val lugarArray = obj.getJSONArray("places")
            for (i in 0 until lugarArray.length()){
                val lugarInfo = lugarArray.getJSONObject(i)
                val lugar = LugaresSanAndres (
                    lugarInfo.getString("nombre"),
                    lugarInfo.getString("urlFoto"),
                    lugarInfo.getString("comentario"),
                    lugarInfo.getString("descripcion"),
                    lugarInfo.getString("puntuacion")
                )
                Log.d(TAG, "generateContacts: $lugar")
                lugares.add(lugar)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        val adapter = CustomAdapter(this@MainActivity, lugares)
        recycler.adapter = adapter
    }
}
