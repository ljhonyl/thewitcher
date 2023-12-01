package es.tiernoparla.thewitcher.vistamodelo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO

class PersonajeVistaModelo(private val datosDAO: BaseDatosDAO) :ViewModel() {

    private val listaPersonajes=MutableLiveData<List<Personaje>>()

    fun recuperarDatos(context: Context){
        listaPersonajes.postValue(datosDAO.seleccionarPersonajes())
    }
    fun seleccionarPersonajes(){
        datosDAO.seleccionarPersonajes()
    }

    fun insertarPersonaje(personaje: Personaje){
        datosDAO.insertar(personaje)
    }

    fun eliminarPersonaje(personaje: Personaje){
        datosDAO.eliminarPersonaje(personaje)
    }
}