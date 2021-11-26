package com.example.programacionmovilciclo4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.nio.channels.DatagramChannel.open
import java.nio.channels.Pipe.open
import java.nio.charset.Charset

class CustomAdapter(
    private var context: Context,
    private var nombres: ArrayList<String>,
    private var details: ArrayList<String>,
    private var puntuacion: ArrayList<String>
    //private var images: ArrayList<String>

): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = nombres[i]
        viewHolder.itemDetail.text = details[i]
        viewHolder.itemPuntuacion.text = puntuacion[i]
        //viewHolder.itemImage.setImageResource(Integer.parseInt(images[i]))
        Glide.with(context)
            .load("https://cdn.pixabay.com/photo/2021/11/08/23/29/nature-6780354_960_720.jpg")
            .into(viewHolder.itemImage)
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, nombres[i], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return nombres.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemPuntuacion: TextView
        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_nombre)
            itemDetail = itemView.findViewById(R.id.item_comentario)
            itemPuntuacion = itemView.findViewById(R.id.item_puntuacion)
        }
    }

}