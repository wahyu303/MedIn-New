package com.example.formtubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formtubes.databinding.ActivityMenuSambunganBinding
import com.example.formtubes.databinding.ActivityProfileBinding

class MenuSambungan : AppCompatActivity() {
    private lateinit var binding: ActivityMenuSambunganBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSambunganBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pindahHalaman = binding.btnkeProfile
        pindahHalaman.setOnClickListener {
            val intent = Intent(this, HalamanProfil::class.java)
            startActivity(intent)
        }
    }
}