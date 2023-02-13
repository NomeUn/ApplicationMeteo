package com.example.Meteo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationmeteo.R
import kotlinx.coroutines.*

class AffichageMeteo : AppCompatActivity() {
    var stop:Boolean = true
    lateinit var tvMessages:TextView
    lateinit var btnRecommencer:Button
    lateinit var pbJauge:ProgressBar
    lateinit var rvListeVilles:RecyclerView
    var listeVilles:MutableList<DonneesMeteo> = mutableListOf()// donnees de test : DonneesMeteo("Rennes", 10.5, "nuageux")
    private val villes = listOf<String>("Rennes", "Paris", "Nantes", "Bordeaux", "Lyon")
    val adapter = DonneesAdapter(listeVilles)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affichage_meteo)

        tvMessages = findViewById<TextView>(R.id.tvMessages)
        btnRecommencer = findViewById<Button>(R.id.btnRecommencer)
        pbJauge = findViewById<ProgressBar>(R.id.pbJauge)
        rvListeVilles = findViewById<RecyclerView>(R.id.rvListeVilles)


        rvListeVilles.adapter = adapter
        rvListeVilles.layoutManager = LinearLayoutManager(this)


        btnRecommencer.setOnClickListener{
            telechargementDonnees()
        }

        telechargementDonnees()


    }



    //fonction qui gère le traitement de l'affichage et fait appel à la classe gérant le téléchargement des données météorologique grâce à une API
    @SuppressLint("NotifyDataSetChanged")
    fun telechargementDonnees(){
        // mise à jour de l'affichage
        btnRecommencer.visibility = View.INVISIBLE
        pbJauge.visibility = View.VISIBLE
        stop = false
        rvListeVilles.visibility = View.INVISIBLE

        changeText(tvMessages)

        //coroutine faisant appel à la fonciton de téléchargement des données grâce à l'API
        GlobalScope.launch(Dispatchers.Main) {
            val telechargementDonneesMeteo = TelechargementDonneesMeteo()
            for (i in 0..4) {

                //l'appel à l'API grâce à la classe TelechargemetDonneesMeteo pas encore fonctionnel

                //telechargementDonneesMeteo.telechargementDonnees(villes[i])
                delay(10000)
                pbJauge.setProgress(i*100/5)
            }

            //mise à jour de l'affichage
            stop = true
            btnRecommencer.visibility = View.VISIBLE
            pbJauge.visibility = View.INVISIBLE
            adapter.notifyDataSetChanged()
            rvListeVilles.visibility = View.VISIBLE
        }


    }

    //fonction avec une coroutine qui fait défiler différents textes
    fun changeText(textView: TextView) {
        val textes = listOf("Nous téléchargeons les données…", "C’est presque fini…", "Plus que quelques secondes avant d’avoir le résultat…")
        var index = 0

        GlobalScope.launch(Dispatchers.Main) {
            while (!stop) {
                textView.text = textes[index % textes.size]
                index++
                delay(6000)
            }
            textView.text = getString(R.string.Fin)
        }
    }




}