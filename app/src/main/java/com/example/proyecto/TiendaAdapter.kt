package com.example.proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TiendaAdapter(private val productos: List<ProductoTienda>) :
    RecyclerView.Adapter<TiendaAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto_tienda, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = productos.size

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.product_name_tienda)
        private val precioTextView: TextView = itemView.findViewById(R.id.product_price_tienda)
        private val imageView: ImageView = itemView.findViewById(R.id.product_image_tienda)
        private val buyButton: Button = itemView.findViewById(R.id.buy_button_tienda)

        fun bind(producto: ProductoTienda) {
            nombreTextView.text = producto.nombre
            precioTextView.text = "${producto.precio} USD"
            imageView.setImageResource(producto.imagen)
            buyButton.setOnClickListener {
                // Aquí puedes agregar la lógica de compra
            }
        }
    }
}
