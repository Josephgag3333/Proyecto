package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var registrarDBHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout con ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar SQLiteHelper
        registrarDBHelper = SQLiteHelper(this)

        // Configurar el botón de Iniciar Sesión
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Verificar que los campos no estén vacíos
            if (email.isNotBlank() && password.isNotBlank()) {
                val inicioExitoso = registrarDBHelper.iniciarSesion(email, password)

                if (inicioExitoso) {
                    // Mostrar mensaje de inicio de sesión exitoso y redirigir a otra pantalla si es necesario
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí puedes redirigir a la pantalla principal o dashboard
                    // val intent = Intent(this, MainActivity::class.java)
                    // startActivity(intent)
                    val intent = Intent(this, NavigationActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Mostrar mensaje de error si las credenciales no coinciden
                    Toast.makeText(this, "Credenciales incorrectas, intenta nuevamente", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Mostrar mensaje si hay campos vacíos
                Toast.makeText(this, "Por favor, ingresa tu email y contraseña", Toast.LENGTH_LONG).show()
            }
        }

        // Configurar el botón de Registro para llevar al usuario a la pantalla de RegistroActivity
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}