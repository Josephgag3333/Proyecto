package com.example.proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.databinding.FragmentActivityInicioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentActivityInicioBinding
private lateinit var categoriasAdapter: CategoriasAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InicioFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentActivityInicioBinding
    private lateinit var categoriasAdapter: CategoriasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout y inicializar el binding
        binding = FragmentActivityInicioBinding.inflate(inflater, container, false)

        // Configurar el RecyclerView
        categoriasAdapter = CategoriasAdapter(getCategorias())
        binding.recyclerViewCategorias.adapter = categoriasAdapter
        binding.recyclerViewCategorias.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    private fun getCategorias(): List<Categoria> {
        return listOf(
            Categoria("Limpieza", listOf(
                Producto("Gel Limpiador Espumoso 236ml - CeraVe", "Limpieza", R.drawable.primera),
                Producto("Sebium Gel Moussant 200ml - Bioderma", "Limpieza", R.drawable.segunda),
                Producto("Gel Limpiador Espumoso 236ml - CeraVe", "Limpieza", R.drawable.primera),
                Producto("Sebium Gel Moussant 200ml - Bioderma", "Limpieza", R.drawable.segunda),
                Producto("Gel Limpiador Espumoso 236ml - CeraVe", "Limpieza", R.drawable.primera),
                Producto("Sebium Gel Moussant 200ml - Bioderma", "Limpieza", R.drawable.segunda),
                Producto("Gel Limpiador Espumoso 236ml - CeraVe", "Limpieza", R.drawable.primera),
                Producto("Sebium Gel Moussant 200ml - Bioderma", "Limpieza", R.drawable.segunda)
            )),
            Categoria("Hidratación", listOf(
                Producto("Gel Hidratante HydroBoost Ácido Hialurónico 50g - Neutrogena", "Hidratación", R.drawable.primera),
                Producto("Gel Hidratante Facial Hyaluron 100ml - Nivea", "Hidratación", R.drawable.segunda),
                Producto("Gel Hidratante HydroBoost Ácido Hialurónico 50g - Neutrogena", "Hidratación", R.drawable.primera),
                Producto("Gel Hidratante Facial Hyaluron 100ml - Nivea", "Hidratación", R.drawable.segunda),
                Producto("Gel Hidratante HydroBoost Ácido Hialurónico 50g - Neutrogena", "Hidratación", R.drawable.primera),
                Producto("Gel Hidratante Facial Hyaluron 100ml - Nivea", "Hidratación", R.drawable.segunda)
            )),
            Categoria("Protección Solar", listOf(
                Producto("Photoderm AR SPF50+ - Bioderma", "Protección Solar", R.drawable.primera),
                Producto("Anthelios UV Mune400 SPF50+ - La Roche Posay", "Protección Solar", R.drawable.segunda),
                Producto("Photoderm AR SPF50+ - Bioderma", "Protección Solar", R.drawable.primera),
                Producto("Anthelios UV Mune400 SPF50+ - La Roche Posay", "Protección Solar", R.drawable.segunda),
                Producto("Photoderm AR SPF50+ - Bioderma", "Protección Solar", R.drawable.primera),
                Producto("Anthelios UV Mune400 SPF50+ - La Roche Posay", "Protección Solar", R.drawable.segunda)

            ))
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}