package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import es.tiernoparla.thewitcher.Auxiliar
import es.tiernoparla.thewitcher.databinding.ActivityInsercionBinding
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.basedatos.BaseDatosDAO
import es.tiernoparla.thewitcher.vistamodelo.PersonajeVistaModelo
import es.tiernoparla.thewitcher.vistamodelo.PersonajeVistaModeloFactory

/**
 * Vista para añadir personajes
 */
class InsercionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsercionBinding
    private val personajeVistaModelo: PersonajeVistaModelo by viewModels {
        PersonajeVistaModeloFactory(BaseDatosDAO(this, "Personajes", null, 1))
    }
    //variable que permitirá manejar la actividad de seleccionar imagenes
    val pickMedia=registerForActivityResult(PickVisualMedia()){uriImagen->
        if (uriImagen!=null) {
            binding.tvImgInsertada.text = uriImagen.toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInsercionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seleccionarImagen()
        insertarPersonaje()
    }

    /**
     * Logica de la selección de una imagen.
     * Su uri se mostrará en el textView de imagenInsertada
     */
    fun seleccionarImagen(){
        binding.btnSeleccionarImagen.setOnClickListener(){
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
    }

    /**
     * Insercion de un nuevo personaje sigueindo arquitetctura MVVM
     * Nose llama a la base de datos, sino que se llama al viewModel
     */
    fun insertarPersonaje(){
        var msg="Los campos nombre, descripción e imagen no pueden estar vacíos"
        binding.btnInsertar.setOnClickListener(){
            if(binding.etNombreInsertar.text.isNotEmpty() && binding.etDescripcionInsertar.text.isNotEmpty() && binding.tvImgInsertada.text.isNotEmpty()){
                val builder = AlertDialog.Builder(this)
                builder.setTitle(Auxiliar.TIPO_AVISO)
                    .setMessage(msg)
                    .setPositiveButton(Auxiliar.OK) { _, _ -> //inserto si pulso en ok
                        val nombre=binding.etNombreInsertar.text.toString()
                        val alias=binding.etAliasInsertar.text.toString()
                        val raza=binding.etRazaInsertar.text.toString()
                        val descripcion=binding.etDescripcionInsertar.text.toString()
                        val imagen=binding.tvImgInsertada.text.toString()
                        val personaje=Personaje(nombre,alias,raza,descripcion,imagen)

                        personajeVistaModelo.insertarPersonaje(personaje)
                        msg="Personaje insertado"
                        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
                    }.setNegativeButton(Auxiliar.CANCELAR) { dialog, _ ->
                        dialog.dismiss()
                    }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            else{
                Auxiliar.mostrarAviso(this,Auxiliar.TIPO_ERROR,msg)
            }
        }
    }

    @Deprecated("Metodo usado sin seguir arquitectura MVVM, reemplazado por insertarPersonaje")
    /**
     * Inserción de un nuevo personaje en la base de datos
     */
    fun insertar(){
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
                Auxiliar.mostrarAviso(this,Auxiliar.TIPO_ERROR,msg)
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