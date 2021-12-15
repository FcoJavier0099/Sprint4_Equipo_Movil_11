package com.example.programacionmovilciclo4

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject

class PrincipalFragment : Fragment() {
    val TAG = "Fragment RecyclerView"
    val utils: Utils = Utils()
    var lugares: ArrayList<LugaresSanAndres> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "On Create")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_principal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "On Created View")
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(view.context)

        recyclerView.layoutManager = linearLayoutManager
        try {
            val obj = JSONObject(utils.loadJsonFromAssets(view.context, "POI.json"))
            Log.d(TAG, "$obj")
            val lugarArray = obj.getJSONArray("places")
            for (i in 0 until lugarArray.length()) {
                val lugarInfo = lugarArray.getJSONObject(i)
                val lugar = LugaresSanAndres(
                    lugarInfo.getString("nombre"),
                    lugarInfo.getString("urlFoto"),
                    lugarInfo.getString("comentario"),
                    lugarInfo.getString("descripcion"),
                    lugarInfo.getString("puntuacion")
                )
                Log.d(ContentValues.TAG, "generateLugares: $lugar")
                lugares.add(lugar)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val adapter = CustomAdapter(this, lugares)
        recyclerView.adapter = adapter

    }
}