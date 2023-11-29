package es.tiernoparla.thewitcher.modelo.adapter

import es.tiernoparla.thewitcher.modelo.Personaje

interface ItemClickListener {
    fun onItemClick(personaje:Personaje)
}