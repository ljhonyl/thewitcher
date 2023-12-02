package es.tiernoparla.thewitcher.vista.adapter

import es.tiernoparla.thewitcher.modelo.Personaje

/**
 * Interfaz que manejara el click en los item del recyclerView
 * @author jhony
 */
interface ItemClickListener {
    fun onItemClick(personaje:Personaje)
}
