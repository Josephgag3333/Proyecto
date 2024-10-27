package com.example.proyecto
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.proyecto.databinding.FragmentMiCuentaBinding

class MiCuentaFragment : Fragment() {
    private lateinit var binding: FragmentMiCuentaBinding
    private lateinit var sqliteHelper: SQLiteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMiCuentaBinding.inflate(inflater, container, false)
        sqliteHelper = SQLiteHelper(requireContext())

        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val emailUsuario = sharedPreferences.getString("user_email", "")

        val usuario = sqliteHelper.obtenerDatosUsuario(emailUsuario!!)
        binding.etNombre.setText(usuario.nombres)
        binding.etApellidos.setText(usuario.apellidos)
        binding.etEmail.setText(usuario.email)
        binding.etTelefono.setText(usuario.telefono)

        binding.btnEditarCuenta.setOnClickListener {
            mostrarDialogoConfirmacionEditar()
        }

        binding.btnEliminarCuenta.setOnClickListener {
            mostrarDialogoConfirmacionEliminar(emailUsuario)
        }

        return binding.root
    }

    private fun mostrarDialogoConfirmacionEditar() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Editar cuenta")
        builder.setMessage("¿Estás seguro que deseas editar los datos de tu cuenta?")

        builder.setPositiveButton("Sí") { dialog, _ ->
            editarCuenta()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun mostrarDialogoConfirmacionEliminar(email: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Eliminar cuenta")
        builder.setMessage("¿Estás seguro que deseas eliminar tu cuenta?")

        builder.setPositiveButton("Sí") { dialog, _ ->
            eliminarCuenta(email)
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun editarCuenta() {
        val nombre = binding.etNombre.text.toString()
        val apellidos = binding.etApellidos.text.toString()
        val telefono = binding.etTelefono.text.toString()

        val emailUsuario = binding.etEmail.text.toString()
        val resultado = sqliteHelper.actualizarDatosUsuario(emailUsuario, nombre, apellidos, telefono)

        if (resultado) {
            Toast.makeText(requireContext(), "Cuenta actualizada con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Error al actualizar los datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eliminarCuenta(email: String) {
        val resultado = sqliteHelper.eliminarCuenta(email)

        if (resultado) {
            Toast.makeText(requireContext(), "Cuenta eliminada con éxito", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        } else {
            Toast.makeText(requireContext(), "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show()
        }
    }
}