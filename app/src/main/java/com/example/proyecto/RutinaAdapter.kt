package com.example.proyecto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RutinaAdapter(private val rutinas: List<Rutina>) :
    RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        // Asegúrate de inflar el layout correcto para el item de la rutina, NO el layout del fragmento
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rutina, parent, false)
        return RutinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, position: Int) {
        val rutina = rutinas[position]
        holder.bind(rutina)
    }

    override fun getItemCount(): Int = rutinas.size

    class RutinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.rutina_titulo)
        private val descripcionTextView: TextView = itemView.findViewById(R.id.rutina_descripcion)

        fun bind(rutina: Rutina) {
            tituloTextView.text = rutina.titulo
            descripcionTextView.text = rutina.descripcion
        }
    }
}
