package es.tiernoparla.thewitcher.vista.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.tiernoparla.thewitcher.databinding.ItemPersonajeBinding
import es.tiernoparla.thewitcher.modelo.Personaje

/**
 * Clase que se encarga de asociar el diseño de la vista del item(item_personaje.xml)
 */
class PersonajeViewHolder(view:View): ViewHolder(view){
    private val binding= ItemPersonajeBinding.bind(view)

    /**
     * Muestra los datos del personaje en las propiedades correspondientes
     */
    fun render(personaje:Personaje){
        binding.tvNombreItem.text=personaje.nombre
        binding.tvProfesionItem.text=personaje.profesion
        Glide.with(binding.ivFotoItem.context).load(personaje.imagen).into(binding.ivFotoItem)
    }
}
