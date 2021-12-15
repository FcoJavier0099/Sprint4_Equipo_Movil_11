package com.example.programacionmovilciclo4

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import org.json.JSONException
import org.json.JSONObject

class VistaFragment : Fragment() {
    var TAG = "Fragment RecyclerView"
    var lugares: ArrayList<LugaresSanAndres> = ArrayList()
    var utils: Utils = Utils()
    private var posicion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "On Create")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vista, container, false)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_vistaFragment_to_principalFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "On Created View")
        arguments?.getInt("posicion")?.let {
            posicion = it
        }
        Log.d("Prueba", posicion.toString())

        try {
            val obj = JSONObject(utils.loadJsonFromAssets(view.context, "POI.json"))
            Log.d(TAG, "$obj")
            val lugarArray = obj.getJSONArray("places")
            val lugarInfo = lugarArray.getJSONObject(posicion)
            val lugar = LugaresSanAndres (
                lugarInfo.getString("nombre"),
                lugarInfo.getString("urlFoto"),
                lugarInfo.getString("comentario"),
                lugarInfo.getString("descripcion"),
                lugarInfo.getString("puntuacion")
            )
            Log.d(ContentValues.TAG, "generateLugares: $lugar")

//            val imageView: ImageView = view.findViewById(R.id.item_image)
            view.findViewById<TextView>(R.id.item_nombre).setText(lugar.nombres.toString())
            view.findViewById<TextView>(R.id.item_descripcion).setText(lugar.descripcion.toString())
//            var puntuacion: TextView = view.findViewById(R.id.item_puntuacion)
            var currentLugares: LugaresSanAndres? = null

            /* Bind Contact name and image. */
            fun bind(lugar: LugaresSanAndres) {
                currentLugares = lugar

//                nombre.text = lugar.nombres
                //descripcion.text = lugar.comentarios
//                puntuacion.text = lugar.puntuacion

//                Glide.with(this)
//                        .load(lugar.urlFoto)
//                        .into(imageView)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}