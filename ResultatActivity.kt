package com.example.cafe001

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultat)

        val nomClient = intent.getStringExtra("NOM_CLIENT") ?: ""
        val nom = intent.getStringExtra("NOM_BOISSON") ?: "Votre commande"
        val image = intent.getIntExtra("IMAGE_BOISSON", 0)
        val details = intent.getStringExtra("DETAILS") ?: ""
        val total = intent.getIntExtra("TOTAL_FINAL", 0)

        findViewById<TextView>(R.id.txtResultatNom).text = nom
        val img = findViewById<ImageView>(R.id.imgResultat)
        if (image == 0) img.visibility = android.view.View.GONE else img.setImageResource(image)
        findViewById<TextView>(R.id.txtResultatClient).text = "Client : $nomClient"
        findViewById<TextView>(R.id.txtResultatDetails).text = details
        findViewById<TextView>(R.id.txtResultatTotal).text = "Total : $total DH"
        findViewById<Button>(R.id.btnRetourMenu).setOnClickListener { finish() }
        findViewById<Button>(R.id.btnRetourAccueil).setOnClickListener {
            val accueil = Intent(this, MainActivity::class.java)
            accueil.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(accueil)
        }
        findViewById<Button>(R.id.btnConfirmerCommande).setOnClickListener {
            Toast.makeText(this, "Commande confirmée pour $nomClient", Toast.LENGTH_LONG).show()
        }
    }
}
