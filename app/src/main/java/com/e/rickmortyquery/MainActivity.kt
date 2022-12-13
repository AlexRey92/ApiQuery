package com.e.rickmortyquery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var Adapter:PersonajeAdapter
    private lateinit  var personaje:PersonajeObjeto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView=findViewById(R.id.searchView)
        recyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        Adapter= PersonajeAdapter()
        recyclerView.adapter=Adapter
        searchView.setOnQueryTextListener(this)
        getRetrofit()

    }

    private fun getPersonaje(query: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val call=getRetrofit().create(ApiService::class.java).getCharacterById("$query")
            val response= call.body()
            runOnUiThread {
                if (call.isSuccessful){
                    personaje= response?.let { Personaje->Personaje.MapearPersonaje() }!!
                    Adapter.notifyDataSetChanged()


                }
            }
        }
    }

    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/character/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            getPersonaje(query)
            }
        return true
    }


    override fun onQueryTextChange(newText: String?): Boolean {
return true   }
}