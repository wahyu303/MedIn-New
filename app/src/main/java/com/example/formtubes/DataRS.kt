package com.example.formtubes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataRS(
    val nama: String,
    val latitude: Double,
    val longitude: Double,
    val alamat: String
) : Parcelable