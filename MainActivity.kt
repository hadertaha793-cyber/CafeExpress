package com.example.cafe001

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var qCafe = 0
    private var qThe = 0
    private var qCroissant = 0
    private var prixPerso = 0
    private var resumePerso = ""

    private lateinit var txtQCafe: TextView
    private lateinit var txtQThe: TextView
    private lateinit var txtQCroissant: TextView
    private lateinit var txtTotal: TextView
    private lateinit var edtNom: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNom = findViewById(R.id.edtNom)
        txtQCafe = findViewById(R.id.txtCafe)
        txtQThe = findViewById(R.id.txtThe)
        txtQCroissant = findViewById(R.id.txtCroissant)
        txtTotal = findViewById(R.id.txtTotal)

        val btnPlusCafe: Button = findViewById(R.id.btnPlusCafe)
        val btnMoinsCafe: Button = findViewById(R.id.btnMoinsCafe)
        val btnPlusThe: Button = findViewById(R.id.btnPlusThe)
        val btnMoinsThe: Button = findViewById(R.id.btnMoinsThe)
        val btnPlusCroissant: Button = findViewById(R.id.btnPlusCroissant)
        val btnMoinsCroissant: Button = findViewById(R.id.btnMoinsCroissant)
        val btnVider: Button = findViewById(R.id.btnVider)
        val btnCommander: Button = findViewById(R.id.btnCommander)
        val btnPersonnaliser: Button = findViewById(R.id.btnPersonnaliser)

        afficherQuantites()
        actualiserTotal()

        btnPlusCafe.setOnClickListener {
            qCafe++
            afficherQuantites()
            actualiserTotal()
        }
        btnMoinsCafe.setOnClickListener {
            if (qCafe > 0) qCafe--
            afficherQuantites()
            actualiserTotal()
        }
        btnPlusThe.setOnClickListener {
            qThe++
            afficherQuantites()
            actualiserTotal()
        }
        btnMoinsThe.setOnClickListener {
            if (qThe > 0) qThe--
            afficherQuantites()
            actualiserTotal()
        }
        btnPlusCroissant.setOnClickListener {
            qCroissant++
            afficherQuantites()
            actualiserTotal()
        }
        btnMoinsCroissant.setOnClickListener {
            if (qCroissant > 0) qCroissant--
            afficherQuantites()
            actualiserTotal()
        }

        btnPersonnaliser.setOnClickListener {
            val intent = Intent(this, Main3Activity::class.java)
            startActivityForResult(intent, CODE_PERSONNALISATION)
        }

        btnCommander.setOnClickListener {
            val nom = edtNom.text.toString().trim()

            if (nom.isEmpty()) {
                edtNom.error = "Entrez le nom du client"
                edtNom.requestFocus()
            } else if (qCafe == 0 && qThe == 0 && qCroissant == 0 && prixPerso == 0) {
                Toast.makeText(this, "Le panier est vide", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, Main2Activity::class.java)
                intent.putExtra("NOM_CLIENT", nom)
                intent.putExtra("QTE_CAFE", qCafe)
                intent.putExtra("QTE_THE", qThe)
                intent.putExtra("QTE_CROISSANT", qCroissant)
                intent.putExtra("TOTAL", calculerTotal())
                intent.putExtra("RESUME_PERSO", resumePerso)
                startActivity(intent)
            }
        }

        btnVider.setOnClickListener {
            qCafe = 0
            qThe = 0
            qCroissant = 0
            prixPerso = 0
            resumePerso = ""
            edtNom.setText("")
            edtNom.error = null
            afficherQuantites()
            actualiserTotal()
            Toast.makeText(this, "Commande annulée", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculerTotal(): Int {
        return qCafe * PRIX_CAFE + qThe * PRIX_THE +
                qCroissant * PRIX_CROISSANT + prixPerso
    }

    private fun afficherQuantites() {
        txtQCafe.text = qCafe.toString()
        txtQThe.text = qThe.toString()
        txtQCroissant.text = qCroissant.toString()
    }

    private fun actualiserTotal() {
        txtTotal.text = "Total : ${calculerTotal()} DH"
    }

    @Deprecated("Utilisé ici car il correspond à la méthode étudiée en cours")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_PERSONNALISATION && resultCode == Activity.RESULT_OK) {
            prixPerso = data?.getIntExtra("PRIX_PERSO", 0) ?: 0
            resumePerso = data?.getStringExtra("RESUME_PERSO") ?: ""
            actualiserTotal()
        }
    }

    companion object {
        private const val CODE_PERSONNALISATION = 1
        private const val PRIX_CAFE = 10
        private const val PRIX_THE = 8
        private const val PRIX_CROISSANT = 6
    }
}
