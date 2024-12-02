package com.example.agropanifb

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agropanifb.databinding.ActivityRegistroTrabajadorBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.agropanifb.Models.Trabajador

class RegistroTrabajador : AppCompatActivity() {

    //Activar viewBinding
    private lateinit var binding: ActivityRegistroTrabajadorBinding

    //Activar firebase DATABASE REALTIME
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroTrabajadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Iniciar la base de datos
        database = FirebaseDatabase.getInstance().getReference("Trabajador")


        binding.btnGuardarTrabajador.setOnClickListener{
            //Obtener los datos
            val nombre = binding.etNombreTrabajador.text.toString()
            val apellido = binding.etApellidoTrabajador.text.toString()
            val edad = binding.etEdadTrabajador.text.toString()
            //Generar el id random
            val id = database.child("Trabajador").push().key

            if (nombre.isEmpty()){
                binding.etNombreTrabajador.error = "Porfavor, ingrese un nombre"
                return@setOnClickListener
            }
            if (apellido.isEmpty()){
                binding.etApellidoTrabajador.error = "Porfavor, ingrese un apellido"
                return@setOnClickListener
            }
            if (edad.isEmpty()){
                binding.etEdadTrabajador.error = "Porfavor, ingrese una edad"
                return@setOnClickListener
            }

            val trabajador = Trabajador(id,nombre,apellido,edad)

            database.child(id!!).setValue(trabajador).addOnCompleteListener {
                binding.etNombreTrabajador.setText("")
                binding.etApellidoTrabajador.setText("")
                binding.etEdadTrabajador.setText("")
                Snackbar.make(binding.root, "Trabajador Agregado", Snackbar.LENGTH_LONG).show()
            }
            binding.btnVerTrabajador.setOnClickListener{
                val intent = Intent(this,VerTrabajador::class.java)
                startActivity(intent)
            }

        }
    }
}