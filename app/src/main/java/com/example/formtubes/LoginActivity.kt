package com.example.formtubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.formtubes.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")
        sessionManager = SessionManager(this)

        binding.btnlogin.setOnClickListener{
            val etEmailLogin = binding.etEmailLogin.text.toString()
            val etPasswordLogin = binding.etPasswordLogin.text.toString()

            if (etEmailLogin.isNotEmpty() && etPasswordLogin.isNotEmpty()){
                loginUser(etEmailLogin, etPasswordLogin)
            }else{
                Toast.makeText(this@LoginActivity, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textView10.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


//        val pindahhalaman = findViewById<Button>(R.id.btnlogin)
//        pindahhalaman.setOnClickListener {
//            val Intent = Intent(this,HomePage::class.java)
//            startActivity(Intent)
//
//
//        }
//
//        val pindahhalaman1 = findViewById<TextView>(R.id.textView10)
//        pindahhalaman1.setOnClickListener {
//            val Intent = Intent(this,RegisterActivity::class.java)
//            startActivity(Intent)
//
//
//        }
    }

    private fun loginUser (email: String, password: String){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    for (userSnapshot in dataSnapshot.children){
                        val userData = userSnapshot.getValue(UserData::class.java)

                        if (userData != null && userData.password == password){
                            sessionManager.saveUserData(userData)
                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, HomePage::class.java))
                            finish()
                            return
                        }
                    }
                }
                Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()

            }
        })
    }
}