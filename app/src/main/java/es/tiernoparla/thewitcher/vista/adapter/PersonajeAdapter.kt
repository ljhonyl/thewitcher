package es.tiernoparla.thewitcher.vista.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.tiernoparla.thewitcher.R
import es.tiernoparla.thewitcher.modelo.Personaje

/**
 * Clase que se encarga de crear y actulizar el recyclerView y sus elementos
 * @author jhony
 * @param listaPersonajes lista que se mostrará en el recyclerView
 * @param itemClickListener objeto que implementa interfaz (implementará sus métodos)
 */
class PersonajeAdapter(private val listaPersonajes: List<Personaje>,private val itemClickListener: ItemClickListener): RecyclerView.Adapter<PersonajeViewHolder>() {

    /**
     * Metodo que infla la vista de la lista con su diseño del elemto
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return PersonajeViewHolder(layoutInflater.inflate(R.layout.item_personaje,parent,false))
    }

    override fun getItemCount(): Int {
        return listaPersonajes.size
    }

    /**
     * Pinta cada en una posición del recyclerView.
     * holder es objeto de PersonajeViewHolder, clase que maneja la información
     * de la vista de cada elemento de la lista
     */
    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item=listaPersonajes[position]
        holder.render(item)
        holder.itemView.setOnClickListener{itemClickListener.onItemClick(item)}
    }
}
