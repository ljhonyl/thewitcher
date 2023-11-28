package es.tiernoparla.thewitcher.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.tiernoparla.thewitcher.R
import es.tiernoparla.thewitcher.databinding.ActivityMenuBinding
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.adapter.PersonajeAdapter
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarRecyclerView()
    }
    fun cargarRecyclerView(){
        var bd=BaseDatosDAO(this,"Personajes",null,1)
        val listaPersonajes: List<Personaje> = bd.seleccionarRecycler()

        binding.recyclerPersonjes.layoutManager=LinearLayoutManager(this)
        binding.recyclerPersonjes.adapter=PersonajeAdapter(listaPersonajes)
    }
}