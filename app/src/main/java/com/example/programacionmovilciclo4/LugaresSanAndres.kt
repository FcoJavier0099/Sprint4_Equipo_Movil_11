package com.example.programacionmovilciclo4

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LugaresSanAndres(
    val nombres: String,
    val urlFoto: String,
    val comentarios: String,
    val descripcion: String,
    val puntuacion: String
): Parcelable