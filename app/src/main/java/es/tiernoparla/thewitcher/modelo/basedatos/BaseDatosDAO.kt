package es.tiernoparla.thewitcher.modelo.basedatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.tiernoparla.thewitcher.modelo.Personaje
import es.tiernoparla.thewitcher.modelo.FicheroMock.FicheroMock

class BaseDatosDAO (context : Context?, name : String?, factory : SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    val crearTablaPersonajes="CREATE TABLE Personajes(Codigo integer primary key autoincrement, Nombre Text, Alias, Raza text, Descripcion text, Imagen text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(crearTablaPersonajes)
        cargaInicialTabla(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun seleccionarPersonajes(): List<Personaje> {
        val listaPersonajes = ArrayList<Personaje>()
        val db = this.readableDatabase
        val query = "SELECT * FROM Personajes"
        val cursor: Cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val codigo=cursor.getInt(cursor.getColumnIndexOrThrow("Codigo"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"))
                val alias = cursor.getString(cursor.getColumnIndexOrThrow("Alias"))
                val raza = cursor.getString(cursor.getColumnIndexOrThrow("Raza"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("Descripcion"))
                val imagen = cursor.getString(cursor.getColumnIndexOrThrow("Imagen"))

                val personaje = Personaje(codigo,nombre, alias, raza, descripcion, imagen)
                listaPersonajes.add(personaje)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return listaPersonajes
    }

    private fun cargaInicialTabla(db: SQLiteDatabase?) {
        val ficheroMock = FicheroMock()

        for (personaje in ficheroMock.getListaPersonajes()) {
            val contentValues=generarContentValues(personaje)
            db?.insert("Personajes", null, contentValues)
        }
    }

    fun insertar(personaje:Personaje){
        val db=this.writableDatabase
        val contentValues=generarContentValues(personaje)
        db?.insert("Personajes",null,contentValues)
    }

    fun eliminarPersonaje(personaje: Personaje){
        val query="DELETE FROM Personajes where Codigo=(?)"
        val db=this.writableDatabase
        val id=personaje.id
        val values= arrayOf(id)
        db.execSQL(query,values)
    }

    private fun generarContentValues(personaje: Personaje): ContentValues{
        val cv = ContentValues()
        cv.put("Nombre", personaje.nombre)
        cv.put("Alias", personaje.alias)
        cv.put("Raza", personaje.raza)
        cv.put("Descripcion", personaje.descripcion)
        cv.put("Imagen", personaje.imagen)
        return  cv
    }
}