package com.example.formtubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSubmit: Button
    private val spinnerIds = arrayOf(
        R.id.spinner1, R.id.spinner2, R.id.spinner3, R.id.spinner4, R.id.spinner5,
        R.id.spinner6, R.id.spinner7, R.id.spinner8, R.id.spinner9, R.id.spinner10,
        R.id.spinner11, R.id.spinner12, R.id.spinner13, R.id.spinner14, R.id.spinner15,
        R.id.spinner16, R.id.spinner17, R.id.spinner18, R.id.spinner19, R.id.spinner20,
        R.id.spinner21, R.id.spinner22, R.id.spinner23, R.id.spinner24, R.id.spinner25,
        R.id.spinner26
    )

    private var pilihan1 = 0
    private var pilihan2 = 0
    private var pilihan3 = 0
    private var pilihan4 = 0
    private var pilihan5 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pindahhalaman = findViewById<Button>(R.id.btsubmit)
        pindahhalaman.setOnClickListener {
            val intent = Intent(this,Halaman3::class.java)
            intent.putExtra("pilihan1", pilihan1)
            intent.putExtra("pilihan2", pilihan2)
            intent.putExtra("pilihan3", pilihan3)
            intent.putExtra("pilihan4", pilihan4)
            intent.putExtra("pilihan5", pilihan5)
            startActivity(intent)
        }

        // Inisialisasi button
        buttonSubmit = findViewById(R.id.btsubmit)
        buttonSubmit.isEnabled = false  // Menonaktifkan tombol secara default

        for (spinnerId in spinnerIds) {
            val spinner: Spinner = findViewById(spinnerId)
            val choices = resources.getStringArray(R.array.choices)

            val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choices) {
                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getDropDownView(position, convertView, parent) as TextView

                    // Menonaktifkan opsi "-" di dropdown
                    if (position == 0) {
                        view.isEnabled = false
                    } else {
                        view.isEnabled = true
                    }

                    return view
                }
            }

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                    val selectedChoice = parentView?.getItemAtPosition(position).toString()

                    // Mengubah latar belakang spinner berdasarkan pilihan
                    when (selectedChoice) {
                        "Ya" -> spinner.setBackgroundResource(R.drawable.spinneryes)
                        "Tidak" -> spinner.setBackgroundResource(R.drawable.spinnerno)
                        else -> spinner.setBackgroundResource(R.drawable.spinnerdefault)
                    }
                    // Menyimpan nilai yang dipilih ke dalam variabel yang sesuai
                    when (spinnerId) {
                        R.id.spinner1 -> pilihan1 = if (selectedChoice == "Ya") 1 else 0
                        R.id.spinner2 -> pilihan2 = if (selectedChoice == "Ya") 1 else 0
                        R.id.spinner3 -> pilihan3 = if (selectedChoice == "Ya") 1 else 0
                        R.id.spinner4 -> pilihan4 = if (selectedChoice == "Ya") 1 else 0
                        R.id.spinner5 -> pilihan5 = if (selectedChoice == "Ya") 1 else 0
                    }

                    // Memeriksa apakah ada yang memilih "-"
                    checkForInvalidSelections()
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // Mengatur latar belakang dari drawable state selector spinner_background_default.xml jika tidak ada yang dipilih
                    spinner.setBackgroundResource(R.drawable.spinnerdefault)

                    // Memeriksa apakah ada yang memilih "-"
                    checkForInvalidSelections()
                }
            }
        }
    }

    // Fungsi untuk memeriksa pilihan yang tidak valid ("-" terpilih) dan menonaktifkan/mengaktifkan tombol sesuai
    private fun checkForInvalidSelections() {
        for (spinnerId in spinnerIds) {
            val spinner: Spinner = findViewById(spinnerId)
            val selectedChoice = spinner.selectedItem.toString()

            // Jika ada spinner yang memilih "-", menonaktifkan tombol dan mengembalikan
            if (selectedChoice == "-") {
                buttonSubmit.isEnabled = false
                return
            }
        }

        // Jika tidak ada spinner yang memilih "-", mengaktifkan tombol
        buttonSubmit.isEnabled = true
                }
            }
