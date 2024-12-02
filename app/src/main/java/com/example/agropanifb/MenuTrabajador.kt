package com.example.agropanifb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuTrabajador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_trabajador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Configurar el botón para redirigir a la actividad Registrar trabajador
        val btnRegistrarEmpleado: Button = findViewById(R.id.btnregistrarempleado)
        btnRegistrarEmpleado.setOnClickListener {
            val intent = Intent(this, RegistroTrabajador ::class.java)
            startActivity(intent)
        }
        // Configurar el botón para redirigir a la actividad Ver trabajador
        val btnVerTrabajador: Button = findViewById(R.id.btnverempleados)
        btnVerTrabajador.setOnClickListener {
            val intent = Intent(this, VerTrabajador ::class.java)
            startActivity(intent)
        }

        // Configurar el BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)




        // Manejar clics en los ítems del menú
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item1 -> {
                    // Acción para "Menu" (actual actividad, puedes omitir esta parte o cambiarla si lo prefieres)
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                    true
                }
                R.id.item2 -> {
                    // Navegar a "Menu Cosecha"
                    val intent = Intent(this, MenuCosecha::class.java)
                    startActivity(intent)
                    true
                }
                R.id.item3 -> {
                    // Navegar a "Menu Trabajadores"
                    val intent = Intent(this, MenuTrabajador::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}