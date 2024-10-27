package com.example.proyecto
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.FragmentActivityInicioBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InicioFragment : Fragment() {

    private lateinit var binding: FragmentActivityInicioBinding
    private lateinit var categoriasAdapter: CategoriasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityInicioBinding.inflate(inflater, container, false)

        categoriasAdapter = CategoriasAdapter(getCategorias())
        Log.d("InicioFragment", "Adapter item count: ${categoriasAdapter.itemCount}")
        binding.recyclerViewCategorias.adapter = categoriasAdapter
        binding.recyclerViewCategorias.layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerViewCategorias.addItemDecoration(
            object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
                ) {
                    outRect.top = 0
                    outRect.bottom = 8.dpToPx()
                }
            }
        )

        return binding.root
    }

    fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun getCategorias(): List<Categoria> {
        return listOf(
            Categoria("Limpieza", listOf(
                Producto("Gel Limpiador Espumoso 236ml - CeraVe", "Limpieza", R.drawable.primera),
                Producto("Sebium Gel Moussant 200ml - Bioderma", "Limpieza", R.drawable.segunda),
                Producto("Gel Hidratante HydroBoost Ácido Hialurónico 50g - Neutrogena", "Hidratación", R.drawable.primera),
                Producto("Gel Hidratante Facial Hyaluron 100ml - Nivea", "Hidratación", R.drawable.segunda),
                Producto("Gel Hidratante HydroBoost Ácido Hialurónico 50g - Neutrogena", "Hidratación", R.drawable.primera),

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
}
