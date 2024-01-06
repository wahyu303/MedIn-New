package com.example.formtubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.formtubes.databinding.ActivityProfileBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HalamanProfil : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var userData: UserData
    private lateinit var databaseReference: DatabaseReference
    private lateinit var sessionManager: SessionManager
    private lateinit var valueEventListener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Inisialisasi database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("users")
        sessionManager = SessionManager(this)

        // Mendapatkan data pengguna dari intent
        userData = sessionManager.getUserData()!!
        Log.d("Namamu", userData.toString())
//        userData = intent.getParcelableExtra("userData")!!

        // Menampilkan data pengguna pada UI
        if (!userData.id.isNullOrEmpty()){
            binding.etNamaprofil2.setText(userData.username)
            binding.etEmailprofil2.setText(userData.email)
            binding.etPassprofil2.setText(userData.password)
            binding.etAlamatprofil2.setText(userData.alamat)

        }

        // Menangani klik tombol "Save Profile"
        binding.btnsaveprofile.setOnClickListener {
            // Mendapatkan data yang diubah oleh pengguna
            val newUsername = binding.etNamaprofil2.text.toString()
            val newEmail = binding.etEmailprofil2.text.toString()
            val newPassword = binding.etPassprofil2.text.toString()
            val newAlamat = binding.etAlamatprofil2.text.toString()

            // Update data pengguna di Firebase
            updateUserData(newUsername, newEmail, newPassword, newAlamat)
        }


        valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Proses data saat data berubah
                // Misalnya, ambil data dan tampilkan

                if (dataSnapshot.exists()){
                    for (userSnapshot in dataSnapshot.children){
                        val userData = userSnapshot.getValue(UserData::class.java)
                        if (userData != null) {
                            sessionManager.saveUserData(userData)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Proses ketika terjadi kesalahan
                println("Failed to read value: ${error.toException()}")
            }
        }

// Mendapatkan data dari database dengan onDataChange
        databaseReference.orderByChild("email").equalTo(userData.email).addValueEventListener(valueEventListener)
    }

    private fun updateUserData(username: String, email: String, password: String, alamat: String) {
        // Update data pengguna di Firebase
        val updatedUserData = UserData(userData.id, username, email, password, alamat)
        databaseReference.child(userData.id!!).setValue(updatedUserData)

        // Tampilkan pesan sukses
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()

        // Kembali ke halaman utama (HomePage)
        startActivity(Intent(this, HomePage::class.java))
        finish()
    }
    override fun onDestroy() {
        // Melepaskan pendengar saat aktivitas dihancurkan
        databaseReference.removeEventListener(valueEventListener)
        super.onDestroy()
    }
}