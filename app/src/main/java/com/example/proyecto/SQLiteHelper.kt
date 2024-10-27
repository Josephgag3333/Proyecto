package com.example.proyecto

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context): SQLiteOpenHelper (context,
    "LoveCareApp.db", null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        // Creación de la tabla Usuarios
        val ordenCreacionUsuarios = """
            CREATE TABLE Usuarios (
                idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                etNombres TEXT NOT NULL,
                etApellidos TEXT NOT NULL,
                etEmail TEXT UNIQUE NOT NULL,
                etTelefono TEXT,
                etPassword TEXT NOT NULL,
                fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """
        db!!.execSQL(ordenCreacionUsuarios)

        // Creación de la tabla Sesiones
        val ordenCreacionSesiones = """
            CREATE TABLE Sesiones (
                idSesion INTEGER PRIMARY KEY AUTOINCREMENT,
                idUsuario INTEGER,
                fechaInicio DATETIME DEFAULT CURRENT_TIMESTAMP,
                fechaFin DATETIME,
                estado TEXT CHECK(estado IN ('activo', 'inactivo')),
                FOREIGN KEY(idUsuario) REFERENCES Usuarios(idUsuario)
            )
        """
        db!!.execSQL(ordenCreacionSesiones)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminar tablas si existen y recrearlas
        db!!.execSQL("DROP TABLE IF EXISTS Usuarios")
        db.execSQL("DROP TABLE IF EXISTS Sesiones")
        onCreate(db)
    }

    // Método para añadir datos a la tabla Usuarios (Registro)
    fun anadirUsuario(
        etNombres: String, etApellidos: String, etEmail: String,
        etTelefono: String, etPassword: String
    ) {
        val datos = ContentValues()
        datos.put("etNombres", etNombres)
        datos.put("etApellidos", etApellidos)
        datos.put("etEmail", etEmail)
        datos.put("etTelefono", etTelefono)
        datos.put("etPassword", etPassword)

        // Guardar los datos con writableDatabase
        val db = this.writableDatabase
        db.insert("Usuarios", null, datos)
        db.close()
    }

    // Método para iniciar sesión
    fun iniciarSesion(etEmail: String, etPassword: String): Boolean {
        val db = this.readableDatabase
        val consulta = "SELECT idUsuario FROM Usuarios WHERE etEmail = ? AND etPassword = ?"
        val cursor: Cursor = db.rawQuery(consulta, arrayOf(etEmail, etPassword))

        // Verificar si existe un usuario con las credenciales proporcionadas
        val resultado = cursor.count > 0
        if (resultado) {
            cursor.moveToFirst()
            val idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario"))
            registrarSesion(idUsuario)
        }

        cursor.close()
        db.close()
        return resultado
    }

    // Método para registrar una nueva sesión
    private fun registrarSesion(idUsuario: Int) {
        val datos = ContentValues()
        datos.put("idUsuario", idUsuario)
        datos.put("estado", "activo")

        val db = this.writableDatabase
        db.insert("Sesiones", null, datos)
        db.close()
    }

    // Método para cerrar sesión
    fun cerrarSesion(idUsuario: Int) {
        val db = this.writableDatabase
        val valores = ContentValues()
        valores.put("estado", "inactivo")
        valores.put("fechaFin", "datetime('now')")

        db.update("Sesiones", valores, "idUsuario = ? AND estado = 'activo'", arrayOf(idUsuario.toString()))
        db.close()
    }
}