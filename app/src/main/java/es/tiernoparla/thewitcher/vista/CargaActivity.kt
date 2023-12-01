package es.tiernoparla.thewitcher.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.tiernoparla.thewitcher.R

class CargaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}