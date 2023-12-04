
package es.tiernoparla.thewitcher.vista

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        binding.btnInsertar.setOnClickListener(){
            verificarPermisos()
        }
    }

    /**
     * Solicitud de permisos
     */
    private fun verificarPermisos(){
        //Permisos concedidos, se prodecerá a insertar
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            insertarPersonaje()
        }
        //Permisos no concedidos aunque tampoco rechazados(no haber preguntado por ellos)
        else{
            pedirPermisos()
        }
    }

    private fun pedirPermisos() {
        val msg="Si no acepta los permisos la imagen podría no funcionar correctamente"
        val msg2="El permiso no ha sido aceptado, para cambiarlo accede a los ajustes de tu dispositivo"
        //Permisos ya rechazados, igualemnte se insertará el personaje
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(Auxiliar.TIPO_AVISO)
                .setMessage(msg2)
                .setPositiveButton(Auxiliar.OK) { _, _ ->
                   insertarPersonaje()
                }
            val dialog: AlertDialog= builder.create()
            dialog.show()
        }
        //Los permisos no estan rechazados, se pregunta si aceptarlos o no
        else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle(Auxiliar.TIPO_AVISO)
                .setMessage(msg)
                .setPositiveButton(Auxiliar.OK) { _, _ ->
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
                }
            val dialog: AlertDialog= builder.create()
            dialog.show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
        var msg2="¿Añadir Personaje?"
        var msgToast="Personaje insertado"
            if(binding.etNombreInsertar.text.isNotEmpty() && binding.etDescripcionInsertar.text.isNotEmpty() && binding.tvImgInsertada.text.isNotEmpty()){
                val builder = AlertDialog.Builder(this)
                builder.setTitle(Auxiliar.TIPO_AVISO)
                    .setMessage(msg2)
                    .setPositiveButton(Auxiliar.OK) { _, _ -> //inserto si pulso en ok
                        val nombre=binding.etNombreInsertar.text.toString()
                        val profesion=binding.etProfesionInsertar.text.toString()
                        val nacion=binding.etNacionInsertar.text.toString()
                        val descripcion=binding.etDescripcionInsertar.text.toString()
                        val imagen=binding.tvImgInsertada.text.toString()
                        val personaje=Personaje(nombre,profesion,nacion,descripcion,imagen)

                        personajeVistaModelo.insertarPersonaje(personaje)
                        Toast.makeText(this,msgToast,Toast.LENGTH_SHORT).show()
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

    @Deprecated("Metodo usado sin seguir arquitectura MVVM, reemplazado por insertarPersonaje")
    /**
     * Inserción de un nuevo personaje en la base de datos
     */
    fun insertar(){
        var msg="Los campos nombre, descripción e imagen no pueden estar vacíos"
        binding.btnInsertar.setOnClickListener(){
            if(binding.etNombreInsertar.text.isNotEmpty() && binding.etDescripcionInsertar.text.isNotEmpty() && binding.tvImgInsertada.text.isNotEmpty()){
                val nombre=binding.etNombreInsertar.text.toString()
                val alias=binding.etProfesionInsertar.text.toString()
                val raza=binding.etNacionInsertar.text.toString()
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