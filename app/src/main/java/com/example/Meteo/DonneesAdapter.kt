package com.example.Meteo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationmeteo.R

//class adapter pour le recycler view avec les données des villes

class DonneesAdapter(
    var donnees: List<DonneesMeteo>
): RecyclerView.Adapter<DonneesAdapter.DonneesViewHolder>() {
    inner class DonneesViewHolder(donneesView: View) : RecyclerView.ViewHolder(donneesView)

    //à la création d'un item du recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonneesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_donnees, parent, false)
        return DonneesViewHolder(view)
    }

    //traitement à faire pour chaque item du recyclerView
    override fun onBindViewHolder(holder: DonneesViewHolder, position: Int) {
        holder.itemView.apply {
            val tvVille = findViewById<TextView>(R.id.tvVille)
            val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
            val tvCouverture = findViewById<TextView>(R.id.tvCouverture)

            tvVille.text = donnees[position].ville
            tvTemperature.text = donnees[position].temperature.toString()
            tvCouverture.text = donnees[position].couverture
        }
    }

    override fun getItemCount(): Int {
        return donnees.size

    }
}