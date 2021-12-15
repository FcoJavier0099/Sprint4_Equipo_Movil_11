package com.example.programacionmovilciclo4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(
    private var context: PrincipalFragment,
    private var lugares: ArrayList<LugaresSanAndres>
): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val lugar = lugares[position]
        viewHolder.bind(lugar = lugar)
        viewHolder.itemView.setOnClickListener { v ->
            if (v != null) {
                val bundle = Bundle().apply {
                    putInt("posicion", position)
                }
                Navigation.findNavController(v).navigate(R.id.action_principalFragment_to_vistaFragment, bundle)
            }
        }

    }

    override fun getItemCount(): Int {
        return lugares.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var imageView: ImageView = itemView.findViewById(R.id.item_image)
        private var nombre: TextView = itemView.findViewById(R.id.item_nombre)
        private var comentario: TextView = itemView.findViewById(R.id.item_comentario)
        private var puntuacion: TextView = itemView.findViewById(R.id.item_puntuacion)
        private var currentLugares: LugaresSanAndres? = null

        /* Bind Contact name and image. */
        fun bind(lugar: LugaresSanAndres) {
            currentLugares = lugar

            nombre.text = lugar.nombres
            comentario.text = lugar.comentarios
            puntuacion.text = lugar.puntuacion

            Glide.with(context)
                .load(lugar.urlFoto)
                .into(imageView)
        }
    }
//        var itemImage: ImageView
//        var itemTitle: TextView
//        var itemDetail: TextView
//        var itemPuntuacion: TextView
//        init {
//            itemImage = itemView.findViewById(R.id.item_image)
//            itemTitle = itemView.findViewById(R.id.item_nombre)
//            itemDetail = itemView.findViewById(R.id.item_comentario)
//            itemPuntuacion = itemView.findViewById(R.id.item_puntuacion)
//        }
//    }

}