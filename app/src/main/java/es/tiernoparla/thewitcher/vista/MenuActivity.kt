package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.tiernoparla.thewitcher.databinding.ActivityMenuBinding
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.adapter.ItemClickListener
import es.tiernoparla.thewitcher.modelo.adapter.PersonajeAdapter
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarRecyclerView()
        mostrarVistaInsertar()
    }

    /**
     * Función que recogerá la información necesaria para ser mostrada en el recyclerview
     * También se definirá el adapter del mismo
     */
    fun cargarRecyclerView(){
        var bd=BaseDatosDAO(this,"Personajes",null,1)
        val listaPersonajes: List<Personaje> = bd.seleccionarPersonajes()

        //Impementacion de la interfaz por medio de un objeto sencillo
        val itemClickListener = object : ItemClickListener {
            override fun onItemClick(personaje: Personaje) {
                mostrarVistaPersonaje(personaje)
            }
        }
        binding.recyclerPersonjes.layoutManager=LinearLayoutManager(this)
        binding.recyclerPersonjes.adapter=PersonajeAdapter(listaPersonajes,itemClickListener)
    }

    /**
     * Metodo que se ejecutará al pulsar un item en el reciclerview
     * Nos mostrara la informacion completa del personaje
     * @param personaje variable que sera parcelizada para poder pasar el objeto
     * a la vista en detalle del personaje
     */
    fun mostrarVistaPersonaje(personaje: Personaje){
            val intent=Intent(this, PersonajeActivity::class.java)
            intent.putExtra("personaje",personaje)
            startActivity(intent)
            this.finish()
    }


    /**
     * Navegación a la vista de inserción
     */
    fun mostrarVistaInsertar(){
        binding.fabInsertar.setOnClickListener(){
            val intent=Intent(this,InsercionActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
    }
}