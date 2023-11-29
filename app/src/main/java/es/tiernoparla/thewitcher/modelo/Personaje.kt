package es.tiernoparla.thewitcher.modelo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Personaje (val nombre:String,val alias:String,val raza:String,val descripcion:String,val imagen:String): Parcelable