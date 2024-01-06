package com.example.formtubes

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MySession", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Nama-nama kunci untuk data sesi
    private val KEY_DATA_SESSION_USER = "dataSessionUser"

    // Fungsi untuk menyimpan sesi login
    fun saveUserData(userData: UserData) {
        // Mengonversi objek UserData ke dalam bentuk JSON
        val userDataJson = Gson().toJson(userData)
        editor.putString(KEY_DATA_SESSION_USER, userDataJson)

        editor.apply()
    }

    // Fungsi untuk mendapatkan nilai dataSessionUser dari sesi
    fun getUserData(): UserData? {
        val userDataJson = sharedPreferences.getString(KEY_DATA_SESSION_USER, null)

        // Mengonversi JSON ke objek UserData
        return if (userDataJson != null) {
            Gson().fromJson(userDataJson, UserData::class.java)
        } else {
            null
        }
    }

    // Fungsi untuk logout dan menghapus data sesi
    fun logout() {
        editor.clear()
        editor.apply()
    }
}
