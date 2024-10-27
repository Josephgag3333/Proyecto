package com.example.proyecto
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TiendaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tiendaAdapter: TiendaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activity_tienda, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewProductosTienda)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(
            ProductoTienda("Gel Limpiador Espumoso", 15.99, R.drawable.item_vector_tienda),
            ProductoTienda("Sebium Gel Moussant", 13.50, R.drawable.item_vector_tienda),
            ProductoTienda("Gel Hidratante HydroBoost", 19.99, R.drawable.item_vector_tienda),
            ProductoTienda("Photoderm AR SPF50+", 22.00, R.drawable.item_vector_tienda),
            ProductoTienda("Anthelios UV Mune400 SPF50+", 18.50, R.drawable.item_vector_tienda)
        )

        tiendaAdapter = TiendaAdapter(productos)
        recyclerView.adapter = tiendaAdapter

        return view
    }
}
