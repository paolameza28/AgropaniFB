package com.example.agropanifb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agropanifb.Models.Trabajador
import com.example.agropanifb.R

class AdapterTrabajador(private var trabajador: ArrayList <Trabajador>):
    RecyclerView.Adapter<AdapterTrabajador.ViewHolder>(){
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre : TextView = itemView.findViewById(R.id.tvNombre)
        val apellido : TextView = itemView.findViewById(R.id.tvApellido)
        val edad : TextView = itemView.findViewById(R.id.tvEdad)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTrabajador.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_trabajadores, parent,false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trabajador = trabajador[position]

        holder.nombre.text = trabajador.nombre
        holder.apellido.text = trabajador.apellido
        holder.edad.text = trabajador.edad



    }

    override fun getItemCount(): Int {
        return  trabajador.size
    }
}