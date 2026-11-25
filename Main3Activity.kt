package com.example.cafe001

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnalisation)

        val rgTaille: RadioGroup = findViewById(R.id.rgTaille)
        val chkSucre: CheckBox = findViewById(R.id.chkSucre)
        val chkCreme: CheckBox = findViewById(R.id.chkCreme)
        val chkCannelle: CheckBox = findViewById(R.id.chkCannelle)
        val swEmporter: SwitchCompat = findViewById(R.id.swEmporter)
        val spnLait: Spinner = findViewById(R.id.spnLait)
        val txtPrix: TextView = findViewById(R.id.txtPrix)
        val txtNom: TextView = findViewById(R.id.txtNomBoisson)
        val txtPrixBase: TextView = findViewById(R.id.txtPrixBase)
        val imgProduit: ImageView = findViewById(R.id.imgProduit)
        val btnRetour: Button = findViewById(R.id.btnRetour)
        val btnCalculer: Button = findViewById(R.id.btnCalculer)

        val nomBoisson = intent.getStringExtra("NOM_BOISSON") ?: "Café"
        val prixBase = intent.getIntExtra("PRIX_BOISSON", 10)
        val imageBoisson = intent.getIntExtra("IMAGE_BOISSON", R.drawable.espresso)
        val categorie = intent.getStringExtra("CATEGORIE_BOISSON") ?: "cafe"
        txtNom.text = nomBoisson
        txtPrixBase.text = "Prix taille Moyen : $prixBase DH"
        imgProduit.setImageResource(imageBoisson)
        findViewById<TextView>(R.id.txtTitrePerso).text = "Personnalise votre boisson"

        val estEau = categorie == "eau"
        val estCafe = categorie == "cafe"
        val laitAutorise = estCafe || categorie == "chocolat"
        val supplementsAutorises = estCafe || categorie == "the" || categorie == "chocolat"

        if (estEau) {
            findViewById<TextView>(R.id.txtLabelTaille).visibility = View.GONE
            rgTaille.visibility = View.GONE
            swEmporter.visibility = View.GONE
        } else {
            rgTaille.check(R.id.rbMoyen)
        }

        if (!supplementsAutorises) {
            findViewById<TextView>(R.id.txtLabelSupplements).visibility = View.GONE
            chkSucre.visibility = View.GONE
            chkCreme.visibility = View.GONE
            chkCannelle.visibility = View.GONE
        } else if (!estCafe) {
            chkCreme.visibility = View.GONE
            chkCannelle.visibility = View.GONE
        }

        if (!laitAutorise) {
            findViewById<TextView>(R.id.txtLabelLait).visibility = View.GONE
            spnLait.visibility = View.GONE
        }

        val laits = listOf("Lait entier", "Lait écrémé", "Lait d'amande (+3 DH)", "Sans lait")
        val prixLaits = listOf(0, 0, 3, 0)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, laits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnLait.adapter = adapter

        btnRetour.setOnClickListener { finish() }

        btnCalculer.setOnClickListener {
            val idTaille = rgTaille.checkedRadioButtonId
            val taille = when (idTaille) {
                R.id.rbPetit -> "Petit"
                R.id.rbMoyen -> "Moyen"
                R.id.rbGrand -> "Grand"
                else -> "Format fixe"
            }
            val prixTaille = if (estEau) prixBase else when (idTaille) {
                R.id.rbPetit -> prixBase - 2
                R.id.rbGrand -> prixBase + 2
                else -> prixBase
            }

            val supplements = mutableListOf<String>()
            var prixSupplements = 0
            if (chkSucre.isChecked) supplements.add("sucre")
            if (chkCreme.isChecked) {
                supplements.add("crème")
                prixSupplements = prixSupplements + 2
            }
            if (chkCannelle.isChecked) {
                supplements.add("cannelle")
                prixSupplements = prixSupplements + 1
            }

            val emporter = if (swEmporter.isChecked) "à emporter" else "sur place"
            val prixEmporter = if (swEmporter.isChecked && !estEau) 2 else 0
            val positionLait = spnLait.selectedItemPosition
            val lait = if (laitAutorise) spnLait.selectedItem.toString() else ""
            val prixLait = if (laitAutorise) prixLaits[positionLait] else 0
            val total = prixTaille + prixSupplements + prixEmporter + prixLait

            val details = if (estEau) {
                "Format fixe : 50 cl"
            } else {
                val choix = mutableListOf<String>()
                choix.add(taille)
                choix.addAll(supplements)
                if (lait.isNotEmpty()) choix.add(lait)
                if (categorie != "jus" && categorie != "smoothie") choix.add(emporter)
                choix.joinToString(", ")
            }

            txtPrix.text = "$details\nPrix : $total DH"

            val resultat = Intent(this, ResultatActivity::class.java)
            resultat.putExtra("NOM_BOISSON", nomBoisson)
            resultat.putExtra("IMAGE_BOISSON", imageBoisson)
            resultat.putExtra("DETAILS", details)
            resultat.putExtra("TOTAL_FINAL", total)
            startActivity(resultat)
        }
    }
}
