package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import es.tiernoparla.thewitcher.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciarSesion()
        registrase()
    }
    fun iniciarSesion(){
        binding.btnLogin.setOnClickListener(){
            if (binding.etUsuario.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.etUsuario.text.toString(),binding.etPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        mostrarMenu()
                    }
                    else{
                    }
                }
            }
        }
    }
    fun registrase(){
        binding.etRegistrarse.setOnClickListener(){
            if (binding.etUsuario.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.etUsuario.text.toString(),binding.etPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        mostrarAviso("AVISO","Usuario registrado correctamente")
                    }
                    else{
                        mostrarAviso("ERROR","Se ha producido un error")
                    }
                }
            }
        }
    }
    fun mostrarAviso(tipo: String, msg: String){
        val builder=AlertDialog.Builder(this)
        builder.setTitle(tipo)
        builder.setMessage(msg)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }
    fun mostrarMenu(){
        val intent:Intent=Intent(this, MenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}