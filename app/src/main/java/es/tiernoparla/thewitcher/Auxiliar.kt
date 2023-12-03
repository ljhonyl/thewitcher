
package es.tiernoparla.thewitcher

import android.content.Context
import androidx.appcompat.app.AlertDialog

/**
 * Clase que servirá de auxiliar para no duplicar el mismo código
 * en diferentes clases
 */
class Auxiliar {

    /**
     * Companion object (estático) para que sea utiliazado a nivel de
     * clase
     * @param context contexto donde se pintara el alertdialog
     * @param tipo tipo de alertdialog
     * @param msg mensaje del alertdialog
     */
    companion object{
        var OK="Ok"
        var CANCELAR="Cancelar"
        var TIPO_AVISO="Aviso"
        var TIPO_ERROR="Error"

        fun mostrarAviso(context: Context, tipo: String, msg: String){
            val builder= AlertDialog.Builder(context)
            builder.setTitle(tipo)
            builder.setMessage(msg)
            builder.setPositiveButton(OK,null)
            val dialog: AlertDialog =builder.create()
            dialog.show()
        }
    }
}