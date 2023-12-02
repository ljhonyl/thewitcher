package es.tiernoparla.thewitcher.vistamodelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO

/**
 * Clase que crea objetos de PersonajeVistaModelo personalizados
 * ya que necesita de un parámetro BaseDatosDAO
 */
class PersonajeVistaModeloFactory(private val datosDAO: BaseDatosDAO) :ViewModelProvider.Factory{

    /**
     * Método que hace un casting del viewModel personalizado a un
     * viewModel genérico
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonajeVistaModelo(datosDAO) as T
    }
}
