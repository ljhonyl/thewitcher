package es.tiernoparla.thewitcher.modelo.basedatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.PersonajesMock.PersonajesMock

class BaseDatosDAO (context : Context?, name : String?, factory : SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    val crearTablaPersonajes="CREATE TABLE Personajes(Codigo int, Nombre Text, Alias, Raza text, Descripcion text, Imagen text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(crearTablaPersonajes)
        cargaInicialTabla(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun seleccionarRecycler(): List<Personaje> {
        val listaPersonajes = ArrayList<Personaje>()
        val db = this.readableDatabase
        val query = "SELECT * FROM Personajes"
        val cursor: Cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val codigo=cursor.getString(cursor.getColumnIndexOrThrow("Codigo")).toInt()
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"))
                val alias = cursor.getString(cursor.getColumnIndexOrThrow("Alias"))
                val raza = cursor.getString(cursor.getColumnIndexOrThrow("Raza"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("Descripcion"))
                val imagen = cursor.getString(cursor.getColumnIndexOrThrow("Imagen"))


                val personaje = Personaje(codigo, nombre, alias, raza, descripcion, imagen)
                listaPersonajes.add(personaje)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return listaPersonajes
    }

    private fun cargaInicialTabla(db: SQLiteDatabase?) {
        val personajesMock = PersonajesMock()

        for (personaje in personajesMock.getListaPersonajes()) {
            val cv = ContentValues()
            cv.put("Codigo", personaje.codigo)
            cv.put("Nombre", personaje.nombre)
            cv.put("Alias", personaje.alias)
            cv.put("Raza", personaje.raza)
            cv.put("Descripcion", personaje.descripcion)
            cv.put("Imagen", personaje.imagen)

            db?.insert("Personajes", null, cv)
        }
    }
}