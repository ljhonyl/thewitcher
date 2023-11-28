package es.tiernoparla.thewitcher.modelo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.tiernoparla.thewitcher.R
import es.tiernoparla.thewitcher.modelo.Personaje

class PersonajeAdapter(private val listaPersonajes: List<Personaje>): RecyclerView.Adapter<PersonajeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return PersonajeViewHolder(layoutInflater.inflate(R.layout.item_personaje,parent,false))
    }

    override fun getItemCount(): Int {
        return listaPersonajes.size
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item=listaPersonajes[position]
        holder.render(item)
    }
}