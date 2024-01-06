package com.example.formtubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Halaman3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman3)

        val pindahhalaman = findViewById<Button>(R.id.btnbacktohome)
        pindahhalaman.setOnClickListener {
            val Intent = Intent(this,HomePage::class.java)
            startActivity(Intent)


        }
        var judul: TextView = findViewById(R.id.textView5)

        // Menerima data dari Intent
        val pilihan1 = intent.getIntExtra("pilihan1", 0)
        val pilihan2 = intent.getIntExtra("pilihan2", 0)
        val pilihan3 = intent.getIntExtra("pilihan3", 0)
        val pilihan4 = intent.getIntExtra("pilihan4", 0)
        val pilihan5 = intent.getIntExtra("pilihan5", 0)


        val databaseAccess = DatabaseAccess.getInstance(applicationContext)
        databaseAccess.open()

//        Menghitung NBC MDD
//        Menghitung prob variable 1 MDD
        var resultMDD1 = 0.0
        resultMDD1 += (databaseAccess.probLikelihoodPembilang("feeling_nervous", pilihan1, "MDD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("feeling_nervous", pilihan1)).toFloat() +
                ((databaseAccess.sumClass("MDD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("feeling_nervous", pilihan1).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 2 MDD
        var resultMDD2 = 0.0
        resultMDD2 += (databaseAccess.probLikelihoodPembilang("panic", pilihan2, "MDD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("panic", pilihan2)).toFloat() +
                ((databaseAccess.sumClass("MDD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("panic", pilihan2).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 3 MDD
        var resultMDD3 = 0.0
        resultMDD3 += (databaseAccess.probLikelihoodPembilang("breathing_rapidly", pilihan3, "MDD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("breathing_rapidly", pilihan3)).toFloat() +
                ((databaseAccess.sumClass("MDD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("breathing_rapidly", pilihan3).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 4 MDD
        var resultMDD4 = 0.0
        resultMDD4 += (databaseAccess.probLikelihoodPembilang("sweating", pilihan4, "MDD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("sweating", pilihan4)).toFloat() +
                ((databaseAccess.sumClass("MDD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("sweating", pilihan4).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 5 MDD
        var resultMDD5 = 0.0
        resultMDD5 += (databaseAccess.probLikelihoodPembilang("trouble_in_concentration", pilihan5, "MDD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("trouble_in_concentration", pilihan5)).toFloat() +
                ((databaseAccess.sumClass("MDD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("trouble_in_concentration", pilihan5).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung probabilitas MDD
        var probMDD = 0.0
        probMDD = resultMDD1*resultMDD2*resultMDD3*resultMDD4*resultMDD5


//===================Menghitung NBC Anexiety================================
//        Menghitung prob variable 1 Anexiety
        var resultAnaxiety1 = 0.0
        resultAnaxiety1 += (databaseAccess.probLikelihoodPembilang("feeling_nervous", pilihan1, "anexiety").toFloat() /
                databaseAccess.probLikelihoodPenyebut("feeling_nervous", pilihan1)).toFloat() +
                ((databaseAccess.sumClass("anexiety").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("feeling_nervous", pilihan1).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 2 anexiety
        var resultAnaxiety2 = 0.0
        resultAnaxiety2 += (databaseAccess.probLikelihoodPembilang("panic", pilihan2, "anexiety").toFloat() /
                databaseAccess.probLikelihoodPenyebut("panic", pilihan2)).toFloat() +
                ((databaseAccess.sumClass("anexiety").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("panic", pilihan2).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 3 anexiety
        var resultAnaxiety3 = 0.0
        resultAnaxiety3 += (databaseAccess.probLikelihoodPembilang("breathing_rapidly", pilihan3, "anexiety").toFloat() /
                databaseAccess.probLikelihoodPenyebut("breathing_rapidly", pilihan3)).toFloat() +
                ((databaseAccess.sumClass("anexiety").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("breathing_rapidly", pilihan3).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 4 anexiety
        var resultAnaxiety4 = 0.0
        resultAnaxiety4 += (databaseAccess.probLikelihoodPembilang("sweating", pilihan4, "anexiety").toFloat() /
                databaseAccess.probLikelihoodPenyebut("sweating", pilihan4)).toFloat() +
                ((databaseAccess.sumClass("anexiety").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("sweating", pilihan4).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 5 anexiety
        var resultAnaxiety5 = 0.0
        resultAnaxiety5 += (databaseAccess.probLikelihoodPembilang("trouble_in_concentration", pilihan5, "anexiety").toFloat() /
                databaseAccess.probLikelihoodPenyebut("trouble_in_concentration", pilihan5)).toFloat() +
                ((databaseAccess.sumClass("anexiety").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("trouble_in_concentration", pilihan5).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung probabilitas anexiety
        var probAnaxiety = 0.0
        probAnaxiety = resultAnaxiety1*resultAnaxiety2*resultAnaxiety3*resultAnaxiety4*resultAnaxiety5


        //===================Menghitung NBC ASD================================
//        Menghitung prob variable 1 ASD
        var resultASD1 = 0.0
        resultASD1 += (databaseAccess.probLikelihoodPembilang("feeling_nervous", pilihan1, "ASD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("feeling_nervous", pilihan1)).toFloat() +
                ((databaseAccess.sumClass("ASD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("feeling_nervous", pilihan1).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 2 ASD
        var resultASD2 = 0.0
        resultASD2 += (databaseAccess.probLikelihoodPembilang("panic", pilihan2, "ASD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("panic", pilihan2)).toFloat() +
                ((databaseAccess.sumClass("ASD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("panic", pilihan2).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 3 ASD
        var resultASD3 = 0.0
        resultASD3 += (databaseAccess.probLikelihoodPembilang("breathing_rapidly", pilihan3, "ASD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("breathing_rapidly", pilihan3)).toFloat() +
                ((databaseAccess.sumClass("ASD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("breathing_rapidly", pilihan3).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 4 ASD
        var resultASD4 = 0.0
        resultASD4 += (databaseAccess.probLikelihoodPembilang("sweating", pilihan4, "ASD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("sweating", pilihan4)).toFloat() +
                ((databaseAccess.sumClass("ASD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("sweating", pilihan4).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 5 ASD
        var resultASD5 = 0.0
        resultASD5 += (databaseAccess.probLikelihoodPembilang("trouble_in_concentration", pilihan5, "ASD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("trouble_in_concentration", pilihan5)).toFloat() +
                ((databaseAccess.sumClass("ASD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("trouble_in_concentration", pilihan5).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung probabilitas ASD
        var probASD = 0.0
        probASD = resultASD1*resultASD2*resultASD3*resultASD4*resultASD5


        //===================Menghitung NBC ADHD================================
//        Menghitung prob variable 1 ADHD
        var resultADHD1 = 0.0
        resultADHD1 += (databaseAccess.probLikelihoodPembilang("feeling_nervous", pilihan1, "ADHD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("feeling_nervous", pilihan1)).toFloat() +
                ((databaseAccess.sumClass("ADHD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("feeling_nervous", pilihan1).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 2 ADHD
        var resultADHD2 = 0.0
        resultADHD2 += (databaseAccess.probLikelihoodPembilang("panic", pilihan2, "ADHD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("panic", pilihan2)).toFloat() +
                ((databaseAccess.sumClass("ADHD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("panic", pilihan2).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 3 ADHD
        var resultADHD3 = 0.0
        resultADHD3 += (databaseAccess.probLikelihoodPembilang("breathing_rapidly", pilihan3, "ADHD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("breathing_rapidly", pilihan3)).toFloat() +
                ((databaseAccess.sumClass("ADHD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("breathing_rapidly", pilihan3).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 4 ADHD
        var resultADHD4 = 0.0
        resultADHD4 += (databaseAccess.probLikelihoodPembilang("sweating", pilihan4, "ADHD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("sweating", pilihan4)).toFloat() +
                ((databaseAccess.sumClass("ADHD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("sweating", pilihan4).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 5 ADHD
        var resultADHD5 = 0.0
        resultADHD5 += (databaseAccess.probLikelihoodPembilang("trouble_in_concentration", pilihan5, "ADHD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("trouble_in_concentration", pilihan5)).toFloat() +
                ((databaseAccess.sumClass("ADHD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("trouble_in_concentration", pilihan5).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung probabilitas ADHD
        var probADHD = 0.0
        probADHD = resultADHD1*resultADHD2*resultADHD3*resultADHD4*resultADHD5


        //===================Menghitung NBC PTSD================================
//        Menghitung prob variable 1 PTSD
        var resultPTSD1 = 0.0
        resultPTSD1 += (databaseAccess.probLikelihoodPembilang("feeling_nervous", pilihan1, "PTSD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("feeling_nervous", pilihan1)).toFloat() +
                ((databaseAccess.sumClass("PTSD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("feeling_nervous", pilihan1).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 2 PTSD
        var resultPTSD2 = 0.0
        resultPTSD2 += (databaseAccess.probLikelihoodPembilang("panic", pilihan2, "PTSD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("panic", pilihan2)).toFloat() +
                ((databaseAccess.sumClass("PTSD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("panic", pilihan2).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 3 PTSD
        var resultPTSD3 = 0.0
        resultPTSD3 += (databaseAccess.probLikelihoodPembilang("breathing_rapidly", pilihan3, "PTSD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("breathing_rapidly", pilihan3)).toFloat() +
                ((databaseAccess.sumClass("PTSD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("breathing_rapidly", pilihan3).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 4 PTSD
        var resultPTSD4 = 0.0
        resultPTSD4 += (databaseAccess.probLikelihoodPembilang("sweating", pilihan4, "PTSD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("sweating", pilihan4)).toFloat() +
                ((databaseAccess.sumClass("PTSD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("sweating", pilihan4).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung prob variable 5 PTSD
        var resultPTSD5 = 0.0
        resultPTSD5 += (databaseAccess.probLikelihoodPembilang("trouble_in_concentration", pilihan5, "PTSD").toFloat() /
                databaseAccess.probLikelihoodPenyebut("trouble_in_concentration", pilihan5)).toFloat() +
                ((databaseAccess.sumClass("PTSD").toFloat() / databaseAccess.total()).toFloat() /
                        (databaseAccess.predictorPriorProb("trouble_in_concentration", pilihan5).toFloat() * databaseAccess.total().toFloat()))

//        Menghitung probabilitas PTSD
        var probPTSD = 0.0
        probPTSD = resultPTSD1*resultPTSD2*resultPTSD3*resultPTSD4*resultPTSD5

//        Melakukan Normalisasi
        probMDD = probMDD/(probMDD+probASD+probADHD+probAnaxiety+probPTSD)
        probASD = probASD/(probMDD+probASD+probADHD+probAnaxiety+probPTSD)
        probADHD = probADHD/(probMDD+probASD+probADHD+probAnaxiety+probPTSD)
        probAnaxiety = probAnaxiety/(probMDD+probASD+probADHD+probAnaxiety+probPTSD)
        probPTSD = probPTSD/(probMDD+probASD+probADHD+probAnaxiety+probPTSD)

        // Menentukan nilai terbesar
        val maxProbability = maxOf(probADHD, probAnaxiety, probMDD, probPTSD, probASD)

        // Menetapkan nilai terbesar ke dalam variabel "prediksi"
        val prediksi = when (maxProbability) {
            probADHD -> "ADHD"
            probAnaxiety -> "Anxiety"
            probMDD -> "MDD"
            probPTSD -> "PTSD"
            probASD -> "ASD"
            else -> "Tidak Diketahui"
        }

        // Menampilkan hasil prediksi
//        println("Hasil Prediksi: $prediksi")
        judul.text = "Kamu mengalami " + prediksi.toString()
    }
}