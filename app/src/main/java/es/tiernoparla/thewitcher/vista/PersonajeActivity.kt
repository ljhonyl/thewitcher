package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import es.tiernoparla.thewitcher.databinding.ActivityMenuBinding
import es.tiernoparla.thewitcher.databinding.ActivityPersonajeBinding
import es.tiernoparla.thewitcher.modelo.Personaje

class PersonajeActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityPersonajeBinding
    private lateinit var personaje:Personaje
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personaje=intent.getParcelableExtra<Personaje>("personaje")!!
        cargarDetallesPersonajes()
    }

    fun cargarDetallesPersonajes(){
        if(personaje!=null){
            binding.tvNombrePersonaje.text=personaje.nombre
            Glide.with(this).load(personaje.imagen).into(binding.ivFotoPersonaje)
            binding.tvAliasPersonaje.text=personaje.alias
            binding.tvRazaPersonaje.text=personaje.raza
            binding.tvDescripcionPersonaje.text=personaje.descripcion
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent=Intent(this,MenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}