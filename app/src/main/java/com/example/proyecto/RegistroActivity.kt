package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var registrarDBHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout con ViewBinding
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar SQLiteHelper
        registrarDBHelper = SQLiteHelper(this)

        // Configurar el botón de Registrarse
        binding.btnRegistrarse.setOnClickListener {
            // Validar que todos los campos estén completos
            if (binding.etNombres.text.isNotBlank() &&
                binding.etApellidos.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank() &&
                binding.etTelefono.text.isNotBlank() &&
                binding.etPassword.text.isNotBlank() &&
                binding.etConfirmPassword.text.isNotBlank()) {

                // Validar que el email tenga un formato correcto y contenga "@"
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) {
                    Toast.makeText(this, "Por favor, ingresa un correo electrónico válido con '@'", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Verificar que la contraseña y la confirmación coincidan
                if (binding.etPassword.text.toString() == binding.etConfirmPassword.text.toString()) {
                    val telefono: String = binding.etTelefono.text.toString()

                    // Insertar datos en la base de datos
                    registrarDBHelper.anadirUsuario(
                        binding.etNombres.text.toString(),
                        binding.etApellidos.text.toString(),
                        binding.etEmail.text.toString(),
                        telefono,
                        binding.etPassword.text.toString()
                    )

                    // Limpiar los campos después del registro
                    limpiarCampos()

                    // Mostrar mensaje de éxito
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                    // Redirigir a la pantalla de inicio de sesión
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Finalizar la actividad de registro para que no vuelva al presionar "atrás"

                } else {
                    // Mostrar mensaje de error si las contraseñas no coinciden
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }

            } else {
                // Mostrar mensaje si hay campos vacíos
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Función para limpiar los campos del formulario
    private fun limpiarCampos() {
        binding.etNombres.text.clear()
        binding.etApellidos.text.clear()
        binding.etEmail.text.clear()
        binding.etTelefono.text.clear()
        binding.etPassword.text.clear()
        binding.etConfirmPassword.text.clear()
    }
}