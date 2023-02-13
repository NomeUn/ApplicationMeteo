package com.example.applicationmeteo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.Meteo.AffichageMeteo

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConsultationMeteo = findViewById<Button>(R.id.btnConsultationMeteo)

        btnConsultationMeteo.setOnClickListener{
            Intent(this, AffichageMeteo::class.java).also {
                startActivity(it)
            }
        }

    }


}