package com.example.cafe001

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recap)

        val txtClient: TextView = findViewById(R.id.txtRClient)
        val txtCafe: TextView = findViewById(R.id.txtRCafe)
        val txtThe: TextView = findViewById(R.id.txtRThe)
        val txtCroissant: TextView = findViewById(R.id.txtRCroissant)
        val txtPerso: TextView = findViewById(R.id.txtRPerso)
        val txtTotal: TextView = findViewById(R.id.txtRTotal)
        val btnModifier: Button = findViewById(R.id.btnModifier)
        val btnConfirmer: Button = findViewById(R.id.btnConfirmer)

        val nom = intent.getStringExtra("NOM_CLIENT") ?: "Client"
        val qCafe = intent.getIntExtra("QTE_CAFE", 0)
        val qThe = intent.getIntExtra("QTE_THE", 0)
        val qCroissant = intent.getIntExtra("QTE_CROISSANT", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val resumePerso = intent.getStringExtra("RESUME_PERSO") ?: ""

        txtClient.text = "Client : $nom"
        txtCafe.text = "Café : $qCafe × 10 DH = ${qCafe * 10} DH"
        txtThe.text = "Thé : $qThe × 8 DH = ${qThe * 8} DH"
        txtCroissant.text = "Croissant : $qCroissant × 6 DH = ${qCroissant * 6} DH"
        txtPerso.text = if (resumePerso.isEmpty()) "" else "Personnalisation : $resumePerso"
        txtTotal.text = "Total : $total DH"

        btnModifier.setOnClickListener { finish() }

        btnConfirmer.setOnClickListener {
            Toast.makeText(this, "Commande de $nom envoyée en cuisine", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
