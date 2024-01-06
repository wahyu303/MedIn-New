package com.example.formtubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.formtubes.databinding.ActivityRegisterBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.btnSignup.setOnClickListener {
            val etUsername = binding.etUsername.text.toString()
            val etEmail = binding.etEmail.text.toString()
            val etPassword = binding.etPassword.text.toString()
            val etKonfirmasi = binding.etKonfirmasi.text.toString()
            val etAlamat = binding.etAlamat.text.toString()

            if (etUsername.isNotEmpty() && etEmail.isNotEmpty() && etPassword.isNotEmpty() && etAlamat.isNotEmpty() && etPassword == etKonfirmasi) {
                signupUser(etUsername, etEmail, etPassword, etAlamat)
            } else {
                if (etPassword != etKonfirmasi) {
                    Toast.makeText(this@RegisterActivity, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegisterActivity, "All fields are mandatory", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.textView12.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun signupUser(username: String, email: String, password: String, alamat: String) {
        // Validasi email
        if (!email.endsWith("@gmail.com")) {
            Toast.makeText(this@RegisterActivity, "Invalid email format. Must be a Gmail account.", Toast.LENGTH_SHORT).show()
            return
        }

        // Validasi panjang password
        if (password.length < 8) {
            Toast.makeText(this@RegisterActivity, "Password must be at least 8 characters long.", Toast.LENGTH_SHORT).show()
            return
        }
        // Check if user with the same email already exists
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    val id = databaseReference.push().key
                    val userData = UserData(id, username, email, password, alamat)
                    databaseReference.child(id!!).setValue(userData)
                    Toast.makeText(this@RegisterActivity, "Signup Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "User already exists", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@RegisterActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
//         Setelah pendaftaran berhasil
//        val id = databaseReference.push().key
//        val intent = Intent(this@RegisterActivity, HalamanProfil::class.java)
//        val userData = UserData(id, username, email, password, alamat)
//        intent.putExtra("userData", userData)
//        startActivity(intent)
//        finish()
    }
}