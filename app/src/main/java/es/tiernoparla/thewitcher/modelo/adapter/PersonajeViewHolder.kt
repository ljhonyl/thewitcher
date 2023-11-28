package es.tiernoparla.thewitcher.modelo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.tiernoparla.thewitcher.databinding.ItemPersonajeBinding
import es.tiernoparla.thewitcher.modelo.Personaje

class PersonajeViewHolder(view:View): ViewHolder(view){
    val binding= ItemPersonajeBinding.bind(view)

    fun render(personaje:Personaje){
        binding.tvNombreItem.text=personaje.nombre
        binding.tvAliasItem.text=personaje.alias
        Glide.with(binding.ivFotoItem.context).load(personaje.imagen).into(binding.ivFotoItem)
    }
}