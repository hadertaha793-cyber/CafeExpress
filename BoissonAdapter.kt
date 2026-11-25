package com.example.cafe001

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class BoissonAdapter(
    private val context: Context,
    private val boissons: List<Boisson>,
    private val quantites: MutableList<Int>,
    private val actualiserTotal: () -> Unit
) : BaseAdapter() {
    override fun getCount(): Int = boissons.size
    override fun getItem(position: Int): Any = boissons[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vue = LayoutInflater.from(context).inflate(R.layout.item_boisson, parent, false)
        val boisson = boissons[position]
        vue.findViewById<ImageView>(R.id.imgBoisson).setImageResource(boisson.image)
        vue.findViewById<TextView>(R.id.tvNomBoisson).text = boisson.nom
        vue.findViewById<TextView>(R.id.tvDescriptionBoisson).text = boisson.description
        vue.findViewById<TextView>(R.id.tvPrixBoisson).text = "${boisson.prix} DH"
        vue.findViewById<TextView>(R.id.tvQuantiteBoisson).text = quantites[position].toString()

        vue.findViewById<Button>(R.id.btnPlusBoisson).setOnClickListener {
            quantites[position] = quantites[position] + 1
            notifyDataSetChanged()
            actualiserTotal()
        }
        vue.findViewById<Button>(R.id.btnMoinsBoisson).setOnClickListener {
            if (quantites[position] > 0) {
                quantites[position] = quantites[position] - 1
                notifyDataSetChanged()
                actualiserTotal()
            }
        }
        return vue
    }
}
