package com.example.proyecto
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RutinasFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var rutinaAdapter: RutinaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activity_rutinas, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewRutinas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val rutinas = getRutinas() // Aquí defines tus rutinas
        rutinaAdapter = RutinaAdapter(rutinas)
        recyclerView.adapter = rutinaAdapter

        return view
    }

    private fun getRutinas(): List<Rutina> {
        return listOf(
            Rutina("Rutina Matutina", "Limpieza, hidratación y protector solar para comenzar el día."),
            Rutina("Rutina Nocturna", "Limpieza profunda, suero antioxidante y crema hidratante."),
            Rutina("Rutina para piel seca", "Productos que restauran la hidratación y suavizan la piel."),
            Rutina("Rutina para piel grasa", "Control de oleosidad con productos no comedogénicos."),
            Rutina("Rutina Anti-Edad", "Ingredientes como retinol y ácido hialurónico para reducir las arrugas."),
            Rutina("Rutina Matutina", "Limpieza, hidratación y protector solar para comenzar el día."),
            Rutina("Rutina Nocturna", "Limpieza profunda, suero antioxidante y crema hidratante."),
            Rutina("Rutina para piel seca", "Productos que restauran la hidratación y suavizan la piel."),
            Rutina("Rutina para piel grasa", "Control de oleosidad con productos no comedogénicos."),
            Rutina("Rutina Anti-Edad", "Ingredientes como retinol y ácido hialurónico para reducir las arrugas."),
            Rutina("Rutina Detox", "Elimina toxinas con limpiadores suaves y mascarillas purificantes."),
            Rutina("Cuidado de los poros", "Minimiza los poros con exfoliantes suaves y tónicos de ácido salicílico."),
            Rutina("Rutina Iluminadora", "Vitamina C y exfoliantes suaves para una piel radiante."),
            Rutina("Rutina Minimalista", "Solo lo esencial: limpiador, hidratante y protector solar."),
            Rutina("Rutina para piel sensible", "Productos hipoalergénicos y calmantes como la avena y aloe vera."),
            Rutina("Cuidado con ácido hialurónico", "Hidrata profundamente con sueros ricos en ácido hialurónico."),
            Rutina("Rutina Anti-acné", "Controla brotes con limpiadores con ácido salicílico y geles de peróxido de benzoilo."),
            Rutina("Cuidado pre y post-sol", "Protección solar alta y productos calmantes post-exposición solar."),
            Rutina("Rutina Pre-Maquillaje", "Prepara tu piel para un maquillaje duradero con sueros y primers ligeros."),
            Rutina("Cuidado con antioxidantes", "Protege la piel de los radicales libres con sueros ricos en antioxidantes."),
            Rutina("Cuidado vegano", "Productos 100% veganos que cuidan la piel sin comprometer el planeta."),
            Rutina("Cuidado con retinol", "Prevención de arrugas y manchas usando retinol en la noche."),
            Rutina("Cuidado post-ejercicio", "Limpieza profunda para eliminar el sudor y prevenir imperfecciones."),
            Rutina("Cuidado reparador nocturno", "Mascarillas de noche y bálsamos ricos en nutrientes para regenerar la piel.")
        )
    }
}
