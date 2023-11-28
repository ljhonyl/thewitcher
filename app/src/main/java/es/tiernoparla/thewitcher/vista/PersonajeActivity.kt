package es.tiernoparla.thewitcher.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.tiernoparla.thewitcher.databinding.ActivityMenuBinding

class PersonajeActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}