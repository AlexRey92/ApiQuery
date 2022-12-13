package com.e.rickmortyquery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PersonajeAdapter:ListAdapter<PersonajeObjeto,PersonajeAdapter.ViewHolder>(DiffCallBack) {
    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val IDpersonaje:TextView=view.findViewById(R.id.textViewID)
        val nombre:TextView=view.findViewById(R.id.textViewName)
        val estado:TextView=view.findViewById(R.id.textViewStatus)
        val especie:TextView=view.findViewById(R.id.textViewSpecies)
        val imagen:ImageView=view.findViewById(R.id.imageView)
        val foto= imagen.toString()
        fun OnBind(character:PersonajeObjeto){
            IDpersonaje.text=character.id.toString()
            nombre.text=character.name
            estado.text=character.status
            especie.text=character.type
            Picasso.get().load(foto).into(imagen)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewLinearinflater=LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return ViewHolder(viewLinearinflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personajeHolder=getItem(position)
        holder.OnBind(personajeHolder)
    }
    companion object DiffCallBack : DiffUtil.ItemCallback<PersonajeObjeto>() {
        override fun areItemsTheSame(oldItem: PersonajeObjeto, newItem: PersonajeObjeto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PersonajeObjeto, newItem: PersonajeObjeto): Boolean {
            return oldItem == newItem
        }
    }
}