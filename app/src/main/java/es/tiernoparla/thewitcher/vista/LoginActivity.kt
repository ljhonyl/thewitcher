package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import es.tiernoparla.thewitcher.Auxiliar
import es.tiernoparla.thewitcher.R
import es.tiernoparla.thewitcher.databinding.ActivityLoginBinding

/**
 * Vista de login
 * @author jhony
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Thewitcher)
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciarSesion()
        registrase()
    }

    /**
     * Lógica que se ejecutará al querer iniciar sesión
     */
    private fun iniciarSesion(){
        binding.btnLogin.setOnClickListener(){
            if (binding.etUsuario.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.etUsuario.text.toString(),binding.etPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        mostrarMenu()
                    }
                    else{
                        val msg="Error al iniciar sesión"
                        Auxiliar.mostrarAviso(this, Auxiliar.TIPO_ERROR, msg)
                    }
                }
            }
            else{
                val msg="Error al iniciar sesión"
                Auxiliar.mostrarAviso(this, Auxiliar.TIPO_ERROR, msg)
            }
        }
    }

    /**
     * Lógica que se ejecutará al querer registrarse
     */
    private fun registrase(){
        binding.etRegistrarse.setOnClickListener(){
            if (binding.etUsuario.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.etUsuario.text.toString(),binding.etPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        val msg="Registro exitoso"
                        Auxiliar.mostrarAviso(this, Auxiliar.TIPO_AVISO, msg)
                    }
                    else{
                        val msg="Se ha producido un error"
                        Auxiliar.mostrarAviso(this, Auxiliar.TIPO_ERROR, msg)
                    }
                }
            }
            else{
                val msg="Se ha producido un error"
                Auxiliar.mostrarAviso(this, Auxiliar.TIPO_ERROR, msg)
            }
        }
    }

    /**
     * Cambio de vista a la vista del menú
     */
    private fun mostrarMenu(){
        val intent:Intent=Intent(this, MenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}