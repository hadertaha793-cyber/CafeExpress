package com.example.cafe001

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNom = findViewById<EditText>(R.id.edtNomClient)
        val btnVider = findViewById<Button>(R.id.btnViderNom)
        val btnMenu = findViewById<Button>(R.id.btnVersMenu)

        btnVider.setOnClickListener {
            edtNom.setText("")
            edtNom.error = null
            Toast.makeText(this, "Nom effacé", Toast.LENGTH_SHORT).show()
        }

        btnMenu.setOnClickListener {
            val nom = edtNom.text.toString().trim()
            if (nom.isEmpty()) {
                edtNom.error = "Saisir votre nom"
                edtNom.requestFocus()
                Toast.makeText(this, "Veuillez saisir votre nom", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("NOM_CLIENT", nom)
                startActivity(intent)
            }
        }
    }
}
