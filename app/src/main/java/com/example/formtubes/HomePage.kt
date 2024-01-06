package com.example.formtubes

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.formtubes.databinding.ActivityHomePageBinding
import com.google.gson.Gson
import android.content.Context
import android.content.SharedPreferences
import com.example.formtubes.databinding.ActivityProfileBinding

// ...


class HomePage : AppCompatActivity() {

    private lateinit var detailsText: TextView
    private lateinit var layout: LinearLayout
    private lateinit var expand: CardView
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Inisialisasi SessionManager
        sessionManager = SessionManager(this)

        //ini buat artikel
        detailsText = binding.detailsText
        layout = binding.layouts
        expand = binding.cardview

        layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand.setOnClickListener {
            val visibility = if (detailsText.visibility == View.GONE) View.VISIBLE else View.GONE
            detailsText.visibility = visibility
        }
        //ini akhir artikel

        val pindahHalaman = binding.btnstart
        pindahHalaman.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val pindahHalamanPro = binding.btnkeSambungan
        pindahHalamanPro.setOnClickListener {
//            val userData = sessionManager.getUserData()
//
//            if (userData != null) {
                val intent = Intent(this, MenuSambungan::class.java)
//                intent.putExtra(DATARS_KEY, userData) // Menggunakan konstanta DATARS_KEY
                startActivity(intent)
//            } else {
//                // Handle jika data pengguna tidak ditemukan
//                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show()
//            }
        }

        val pindahHalaman1 = binding.btnpelayanan1
        pindahHalaman1.setOnClickListener {
            val intent = Intent(this, MapsPelayanan::class.java)
            val data = DataRS(
                "RS PKU Muhammadiyah Jogjakarta",
                -7.8011392,
                110.359681,
                "JL.KH. Ahmad Dahlan no.20"
            )
            intent.putExtra(DATARS_KEY, data)
            startActivity(intent)
        }

        val pindahHalaman2 = binding.btnpelayanan2
        pindahHalaman2.setOnClickListener {
            val intent = Intent(this, MapsPelayanan::class.java)
            val data = DataRS(
                "RSUD Kota Yogyakarta",
                -7.8258278,
                110.3754317,
                "Jl. Ki Ageng Pemanahan No. 1"
            )
            intent.putExtra(DATARS_KEY, data)
            startActivity(intent)
        }

        val pindahHalaman3 = binding.btnpelayanan3
        pindahHalaman3.setOnClickListener {
            val intent = Intent(this, MapsPelayanan::class.java)
            val data = DataRS(
                "Jogja International Hospital",
                -7.7575571,
                110.4009975,
                "Jl. Ringroad Utara No. 160"
            )
            intent.putExtra(DATARS_KEY, data)
            startActivity(intent)
        }
    }

    companion object {
        const val DATARS_KEY = "datars_key"
    }
}

