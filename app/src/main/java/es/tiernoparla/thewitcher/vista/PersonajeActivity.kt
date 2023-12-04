
package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import es.tiernoparla.thewitcher.Auxiliar
import es.tiernoparla.thewitcher.databinding.ActivityPersonajeBinding
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO
import es.tiernoparla.thewitcher.vistamodelo.PersonajeVistaModelo
import es.tiernoparla.thewitcher.vistamodelo.PersonajeVistaModeloFactory

/**
 * Vista en detalle de los personajes
 * @author jhony
 */
class PersonajeActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityPersonajeBinding
    private var personaje: Personaje? =null
    private val personajeVistaModelo: PersonajeVistaModelo by viewModels {
        PersonajeVistaModeloFactory(BaseDatosDAO(this, "Personajes", null, 1))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarDetallesPersonajes()
        eliminarPersonaje()
    }

    /**
     * Método que pintará la información del personajeeste sera recogido
     * de los extras que se le pasó en la vista anterior
     */
    private fun cargarDetallesPersonajes(){
        personaje=intent.getParcelableExtra("personaje")
        if(personaje!=null){
            binding.tvNombrePersonaje.text= personaje?.nombre
            Glide.with(this).load(personaje?.imagen).into(binding.ivFotoPersonaje)
            binding.tvProfesionPersonaje.text= personaje?.profesion
            binding.tvNacionPersonaje.text=personaje?.nacion
            binding.tvDescripcionPersonaje.text=personaje?.descripcion
        }
    }

    /**
     * Eliminar siguiendo arquitectura MVVM
     */
    private fun eliminarPersonaje(){
        val msg="¿Eliminar el personaje?"
        binding.fabEliminarPersonaje.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(Auxiliar.TIPO_AVISO)
                .setMessage(msg)
                .setPositiveButton(Auxiliar.OK) { _, _ -> //Elimino si pulso en ok
                    personaje?.let {
                        personajeVistaModelo.eliminarPersonaje(it)
                        onBackPressed()
                    } ?: run {
                        onBackPressed()
                    }
                }.setNegativeButton(Auxiliar.CANCELAR) { dialog, _ ->
                        dialog.dismiss()
                }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
    @Deprecated("Metodo que no sigue MVVM, reemplazado por eliminarPersonaje")
    /**
     * Método por el cual se eliminara el personaje
     */
    private fun eliminar(){
        binding.fabEliminarPersonaje.setOnClickListener {
            val db=BaseDatosDAO(this,"Personajes",null,1)
            db.eliminarPersonaje(personaje!!)
            onBackPressed()
        }

    }

    /**
     * Método que nos devuelve a la vista de la lista
     */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent=Intent(this,MenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}