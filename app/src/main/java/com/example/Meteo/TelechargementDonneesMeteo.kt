package com.example.Meteo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//Classe permettant de récupérer des données météorologique d'une ville en faisant appel à une API
class TelechargementDonneesMeteo {

    private val API_KEY = "dd58156a56cf9bab7b104b1534a17598"

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val meteoService = retrofit.create(MeteoService::class.java)


    //téléchargement des données avec l'API
    fun telechargementDonnees(ville:String) : DonneesMeteo? {

        try {
            //appel à l'API
            val response = meteoService.getMeteo(ville, API_KEY).execute()
            if (response.isSuccessful) {


                //traitement des donnees
                val donneesRecues = response.body()
                if (donneesRecues != null) {
                   /* donnees.ville = donneesRecues.name.toString()
                    donnees.temperature = donneesRecues.main.temp.toDouble()
                    donnees.couverture = donneesRecues.clouds.all.toString()*/
                }
                else{
                    //envoyer message d'erreur
                }
            } else {
                // Pas de reponse d'API
            }
        } catch (e: Exception) {
            // Probleme de connexion
        }

        //à changer
        return null

    }

    interface MeteoService {
        @GET("data/2.5/weather")
        fun getMeteo(
            @Query("q") city: String,
            @Query("appid") apiKey: String
        ): Call<Meteo>
    }

    data class Meteo(
        val main: Main,
        val clouds: Clouds
    )

    data class Main(
        val temp: Float
    )

    data class Clouds(
        val all: Int
    )


}