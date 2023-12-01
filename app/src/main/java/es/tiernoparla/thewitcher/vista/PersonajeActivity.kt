package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.tiernoparla.thewitcher.databinding.ActivityPersonajeBinding
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO

class PersonajeActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityPersonajeBinding
    private lateinit var personaje: Personaje
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
        personaje=intent.getParcelableExtra<Personaje>("personaje")!!
        if(personaje!=null){
            binding.tvNombrePersonaje.text=personaje.nombre
            Glide.with(this).load(personaje.imagen).into(binding.ivFotoPersonaje)
            binding.tvAliasPersonaje.text=personaje.alias
            binding.tvRazaPersonaje.text=personaje.raza
            binding.tvDescripcionPersonaje.text=personaje.descripcion
        }
    }

    /**
     * Método por el cual se eliminara el personaje
     */
    private fun eliminarPersonaje(){
        val db=BaseDatosDAO(this,"Personajes",null,1)
        db.eliminarPersonaje(personaje)
        onBackPressed()
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent=Intent(this,MenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}