package es.tiernoparla.thewitcher

import android.content.Context
import androidx.appcompat.app.AlertDialog

/**
 * Clase que servirá de auxiliar para no duplicar el mismo código
 * en diferentes clases
 */
abstract class Auxiliar {

    /**
     * Companion object (estático) para que sea utiliazado a nivel de
     * clase
     * @param context contexto donde se pintara el alertdialog
     * @param tipo tipo de alertdialog
     * @param msg mensaje del alertdialog
     */
    companion object{
        private var OK="Ok"
        private  var CANCELAR="Cancelar"
        fun mostrarAviso(context: Context, tipo: String, botonSimple: Boolean, msg: String){
            if (botonSimple){
                val builder= AlertDialog.Builder(context)
                builder.setTitle(tipo)
                builder.setMessage(msg)
                builder.setPositiveButton(OK,null)
                val dialog: AlertDialog =builder.create()
                dialog.show()
            }else{
                val builder= AlertDialog.Builder(context)
                builder.setTitle(tipo)
                builder.setMessage(msg)
                builder.setNegativeButton(CANCELAR,null)
                builder.setPositiveButton(OK,null)
                val dialog: AlertDialog =builder.create()
                dialog.show()
            }

        }
        var TIPO_AVISO="Aviso"
        var TIPO_ERROR="Error"
        var BOTON_SIMPLE=true
        var BOTON_DOBLE=false
    }


}