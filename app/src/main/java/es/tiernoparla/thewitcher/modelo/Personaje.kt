package es.tiernoparla.thewitcher.modelo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Personaje (val id: Int, val nombre:String,val alias:String,val raza:String,val descripcion:String,val imagen:String): Parcelable{
    constructor(nombre:String,alias:String,raza:String,descripcion:String,imagen:String): this(0,nombre,alias,raza,descripcion,imagen){
    }
}