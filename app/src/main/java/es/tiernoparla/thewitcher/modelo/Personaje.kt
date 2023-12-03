package es.tiernoparla.thewitcher.modelo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Personaje (val id: Int, val nombre:String,val profesion:String,val nacion:String,val descripcion:String,val imagen:String): Parcelable{
    constructor(nombre:String,profesion: String,nacion: String,descripcion:String,imagen:String): this(0,nombre,profesion,nacion,descripcion,imagen){
    }
}