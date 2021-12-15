package com.example.programacionmovilciclo4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation

class InicioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        view.findViewById<ImageView>(R.id.imageView).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_inicioFragment_to_principalFragment)
        }
        return view
    }

}