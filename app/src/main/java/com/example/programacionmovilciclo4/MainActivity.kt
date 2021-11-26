package com.example.programacionmovilciclo4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //var imagenes: ArrayList<String> = ArrayList()
    var nombres: ArrayList<String> = ArrayList()
    var comentarios: ArrayList<String> = ArrayList()
    var puntuacion: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)

        recyclerView.layoutManager = linearLayoutManager
        try {
            val obj = JSONObject(Utils(this).loadJsonFromAssets(applicationContext, "POI.json"))
            val userArray = obj.getJSONArray("places")
            for (i in 0 until userArray.length()){
                val userInfo = userArray.getJSONObject(i)
               // imagenes.add(userInfo.getString("urlFoto"))
                nombres.add(userInfo.getString("nombre"))
                comentarios.add(userInfo.getString("comentario"))
                puntuacion.add(userInfo.getString(("puntuacion")))
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        val adapter = CustomAdapter(this@MainActivity, nombres, comentarios, puntuacion)

        recyclerView.adapter = adapter
    }
}
