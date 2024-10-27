package com.example.proyecto

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, "LoveCareApp.db", null, 1) {

    companion object {
        private const val TABLE_USERS = "Usuarios"
        private const val TABLE_SESSIONS = "Sesiones"
        private const val COLUMN_USER_ID = "idUsuario"
        private const val COLUMN_USER_FIRSTNAME = "etNombres"
        private const val COLUMN_USER_LASTNAME = "etApellidos"
        private const val COLUMN_USER_EMAIL = "etEmail"
        private const val COLUMN_USER_PHONE = "etTelefono"
        private const val COLUMN_USER_PASSWORD = "etPassword"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Creación de la tabla Usuarios
        val ordenCreacionUsuarios = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_FIRSTNAME TEXT NOT NULL,
                $COLUMN_USER_LASTNAME TEXT NOT NULL,
                $COLUMN_USER_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_USER_PHONE TEXT,
                $COLUMN_USER_PASSWORD TEXT NOT NULL,
                fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """
        db!!.execSQL(ordenCreacionUsuarios)

        // Creación de la tabla Sesiones
        val ordenCreacionSesiones = """
            CREATE TABLE $TABLE_SESSIONS (
                idSesion INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER,
                fechaInicio DATETIME DEFAULT CURRENT_TIMESTAMP,
                fechaFin DATETIME,
                estado TEXT CHECK(estado IN ('activo', 'inactivo')),
                FOREIGN KEY($COLUMN_USER_ID) REFERENCES $TABLE_USERS($COLUMN_USER_ID)
            )
        """
        db.execSQL(ordenCreacionSesiones)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminar tablas si existen y recrearlas
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SESSIONS")
        onCreate(db)
    }

    // Método para añadir datos a la tabla Usuarios (Registro)
    fun anadirUsuario(etNombres: String, etApellidos: String, etEmail: String, etTelefono: String, etPassword: String) {
        val datos = ContentValues().apply {
            put(COLUMN_USER_FIRSTNAME, etNombres)
            put(COLUMN_USER_LASTNAME, etApellidos)
            put(COLUMN_USER_EMAIL, etEmail)
            put(COLUMN_USER_PHONE, etTelefono)
            put(COLUMN_USER_PASSWORD, etPassword)
        }

        writableDatabase.use { db ->
            db.insert(TABLE_USERS, null, datos)
        }
    }

    // Método para iniciar sesión
    fun iniciarSesion(etEmail: String, etPassword: String): Boolean {
        val db = readableDatabase
        val consulta = "SELECT $COLUMN_USER_ID FROM $TABLE_USERS WHERE $COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        val cursor: Cursor = db.rawQuery(consulta, arrayOf(etEmail, etPassword))

        val resultado = cursor.count > 0
        if (resultado) {
            cursor.moveToFirst()
            val idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))
            registrarSesion(idUsuario)
        }

        cursor.close()
        db.close()
        return resultado
    }

    // Método para registrar una nueva sesión
    private fun registrarSesion(idUsuario: Int) {
        val datos = ContentValues().apply {
            put(COLUMN_USER_ID, idUsuario)
            put("estado", "activo")
        }

        writableDatabase.use { db ->
            db.insert(TABLE_SESSIONS, null, datos)
        }
    }

    // Método para cerrar sesión
    fun cerrarSesion(idUsuario: Int) {
        val valores = ContentValues().apply {
            put("estado", "inactivo")
            put("fechaFin", "datetime('now')")
        }

        writableDatabase.use { db ->
            db.update(TABLE_SESSIONS, valores, "$COLUMN_USER_ID = ? AND estado = 'activo'", arrayOf(idUsuario.toString()))
        }
    }

    // Método para obtener el nombre completo del usuario a partir del email
    fun obtenerNombreUsuario(etEmail: String): String {
        val db = readableDatabase
        val consulta = "SELECT $COLUMN_USER_FIRSTNAME, $COLUMN_USER_LASTNAME FROM $TABLE_USERS WHERE $COLUMN_USER_EMAIL = ?"
        val cursor: Cursor = db.rawQuery(consulta, arrayOf(etEmail))
        var nombreCompleto = "Usuario"

        if (cursor.moveToFirst()) {
            val nombres = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_FIRSTNAME))
            val apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_LASTNAME))
            nombreCompleto = "$nombres $apellidos"
        }

        cursor.close()
        db.close()
        return nombreCompleto
    }


    // Queries por si a caso para gestion del Usuario

    // Obtener usuario por ID
    fun obtenerUsuarioPorId(idUsuario: Int): Usuario? {
        val db = readableDatabase
        val consulta = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USER_ID = ?"
        val cursor: Cursor = db.rawQuery(consulta, arrayOf(idUsuario.toString()))

        var usuario: Usuario? = null
        if (cursor.moveToFirst()) {
            val nombres = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_FIRSTNAME))
            val apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_LASTNAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL))
            val telefono = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PHONE))
            usuario = Usuario(idUsuario, nombres, apellidos, email, telefono)
        }

        cursor.close()
        db.close()
        return usuario
    }

    // Eliminar cuenta de usuario por ID
    fun eliminarCuentaUsuario(idUsuario: Int): Boolean {
        val db = writableDatabase
        val resultado = db.delete(TABLE_USERS, "$COLUMN_USER_ID = ?", arrayOf(idUsuario.toString()))
        db.close()
        return resultado > 0
    }

    // Editar los datos de un usuario
    fun editarUsuario(idUsuario: Int, etNombres: String, etApellidos: String, etEmail: String, etTelefono: String): Boolean {
        val valores = ContentValues().apply {
            put(COLUMN_USER_FIRSTNAME, etNombres)
            put(COLUMN_USER_LASTNAME, etApellidos)
            put(COLUMN_USER_EMAIL, etEmail)
            put(COLUMN_USER_PHONE, etTelefono)
        }

        val db = writableDatabase
        val resultado = db.update(TABLE_USERS, valores, "$COLUMN_USER_ID = ?", arrayOf(idUsuario.toString()))
        db.close()
        return resultado > 0
    }
}
