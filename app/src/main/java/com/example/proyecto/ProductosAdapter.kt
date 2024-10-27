package com.example.proyecto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductosAdapter(private val productos: List<Producto>) :
    RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = productos.size

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(producto: Producto) {
            val productName = itemView.findViewById<TextView>(R.id.product_name)
            productName.text = if (producto.nombre.length > 20) {
                "${producto.nombre.take(20)}..."
            } else {
                producto.nombre
            }
            itemView.findViewById<ImageView>(R.id.product_image).setImageResource(producto.imagen)
            itemView.findViewById<Button>(R.id.buy_button).setOnClickListener {
                Toast.makeText(itemView.context, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
            }
        }
    }

}