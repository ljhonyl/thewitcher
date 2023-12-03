package es.tiernoparla.thewitcher.vistamodelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO

/**
 * ViewModel que manejara los datos recogidos de la base de datos y proporcionara
 * una lista de personajes de "solo lectura", pues es esta clase la que maneja
 * los cambios en la capa del modelo
 * @author jhony
 */
class PersonajeVistaModelo(private val datosDAO: BaseDatosDAO) :ViewModel() {


    //Mantiene la lista actualiza
    private val _listaPersonajes=MutableLiveData<List<Personaje>>()
    //Lista que se mantiene actualizada al ser la misma lista que _listaPersonajes
    //La diferencia es que esta es pública
    val listaPersonajes: LiveData<List<Personaje>> get() = _listaPersonajes

    init {
        _listaPersonajes.value=datosDAO.seleccionarPersonajes()

    }

    /**
     * Inserta al personaje en la base de datos
     * @param personaje objeto a insertar
     */
    fun insertarPersonaje(personaje: Personaje){
        datosDAO.insertar(personaje)
        _listaPersonajes.value=datosDAO.seleccionarPersonajes()
    }

    /**
     * Inserta al personaje en la base de datos
     * @param personaje objeto a eliminar
     */
    fun eliminarPersonaje(personaje: Personaje){
        datosDAO.eliminarPersonaje(personaje)
        _listaPersonajes.value=datosDAO.seleccionarPersonajes()
    }
}