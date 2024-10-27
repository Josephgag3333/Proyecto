package com.example.proyecto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriasAdapter(private val categorias: List<Categoria>) :
    RecyclerView.Adapter<CategoriasAdapter.CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria_xml, parent, false)
        return CategoriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = categorias[position]
        holder.bind(categoria)
    }

    override fun getItemCount(): Int = categorias.size

    class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoria: Categoria) {
            itemView.findViewById<TextView>(R.id.category_title).text = categoria.nombre

            val productosRecyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerViewProductos)

            productosRecyclerView.layoutManager = LinearLayoutManager(
                itemView.context, LinearLayoutManager.HORIZONTAL, false
            )

            productosRecyclerView.adapter = ProductosAdapter(categoria.producto)
        }
    }
}