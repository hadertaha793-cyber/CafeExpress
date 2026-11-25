package com.example.cafe001

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var boissons: List<Boisson>
    private lateinit var quantites: MutableList<Int>
    private var nomClient = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        nomClient = intent.getStringExtra("NOM_CLIENT") ?: ""
        boissons = listOf(
            Boisson("Espresso", "Court et intense", 10, R.drawable.espresso, "cafe"),
            Boisson("Café crème", "Espresso et lait chaud", 12, R.drawable.cafe_creme, "cafe"),
            Boisson("Américain", "Espresso allongé d'eau chaude", 12, R.drawable.americano, "cafe"),
            Boisson("Cappuccino", "Mousse de lait onctueuse", 15, R.drawable.cappuccino, "cafe"),
            Boisson("Latte", "Beaucoup de lait, doux", 15, R.drawable.latte, "cafe"),
            Boisson("Moka", "Café et chocolat", 17, R.drawable.moka, "cafe"),
            Boisson("Thé à la menthe", "Thé vert, menthe fraîche", 8, R.drawable.the_menthe, "the"),
            Boisson("Thé vert", "Nature, sans sucre", 9, R.drawable.the_vert, "the"),
            Boisson("Chocolat chaud", "Cacao et lait", 14, R.drawable.chocolat_chaud, "chocolat"),
            Boisson("Jus d'orange", "Pressé minute", 12, R.drawable.jus_orange, "jus"),
            Boisson("Smoothie fraise", "Fraise, banane, yaourt", 18, R.drawable.smoothie_fraise, "smoothie"),
            Boisson("Eau minérale", "50 cl", 5, R.drawable.eau_minerale, "eau")
        )
        quantites = MutableList(boissons.size) { 0 }
        val txtTotal = findViewById<TextView>(R.id.txtMenuTotal)
        fun mettreAJourTotal() {
            var total = 0
            for (i in boissons.indices) total += boissons[i].prix * quantites[i]
            txtTotal.text = "Total : $total DH"
        }
        val adapter = BoissonAdapter(this, boissons, quantites) { mettreAJourTotal() }
        findViewById<ListView>(R.id.lvBoissons).adapter = adapter

        findViewById<Button>(R.id.btnCommanderMenu).setOnClickListener {
            var total = 0
            var nbChoisi = 0
            val lignes = mutableListOf<String>()
            for (i in boissons.indices) {
                if (quantites[i] > 0) {
                    val sousTotal = boissons[i].prix * quantites[i]
                    total += sousTotal
                    nbChoisi += quantites[i]
                    lignes.add("${boissons[i].nom} : ${quantites[i]} × ${boissons[i].prix} DH = $sousTotal DH")
                }
            }
            if (nbChoisi == 0) {
                Toast.makeText(this, "Choisissez au moins une boisson", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultatActivity::class.java)
                intent.putExtra("NOM_CLIENT", nomClient)
                intent.putExtra("DETAILS", lignes.joinToString("\n"))
                intent.putExtra("TOTAL_FINAL", total)
                startActivity(intent)
            }
        }
    }
}
