package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import es.tiernoparla.thewitcher.Auxiliar
import es.tiernoparla.thewitcher.databinding.ActivityInsercionBinding
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO

class InsercionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsercionBinding
    val pickMedia=registerForActivityResult(PickVisualMedia()){
        if (it!=null) {
            binding.tvImgInsertada.text = it.toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInsercionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seleccionarImagen()
        insertarPerosnaje()
    }

    /**
     * Logica de la selección de una imagen.
     * Su uri se mostrará en el textview de imagenInsertada
     */
    fun seleccionarImagen(){
        binding.btnSeleccionarImagen.setOnClickListener(){
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
    }

    /**
     * Inserción de un nuevo personaje en la base de datos
     */
    fun insertarPerosnaje(){
        var msg="Los campos nombre, descripción e imagen no pueden estar vacíos"
        binding.btnInsertar.setOnClickListener(){
            if(binding.etNombreInsertar.text.isNotEmpty() && binding.etDescripcionInsertar.text.isNotEmpty() && binding.tvImgInsertada.text.isNotEmpty()){
                val nombre=binding.etNombreInsertar.text.toString()
                val alias=binding.etAliasInsertar.text.toString()
                val raza=binding.etRazaInsertar.text.toString()
                val descripcion=binding.etDescripcionInsertar.text.toString()
                val imagen=binding.tvImgInsertada.text.toString()
                val personaje=Personaje(nombre,alias,raza,descripcion,imagen)

                val db=BaseDatosDAO(this,"Personajes",null,1)
                db.insertar(personaje)
                msg="Personaje insertado"
                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
            }
            else{
                Auxiliar.mostrarAviso(this,Auxiliar.TIPO_ERROR,Auxiliar.BOTON_SIMPLE,msg)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent= Intent(this,MenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}