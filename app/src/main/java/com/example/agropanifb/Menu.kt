package com.example.agropanifb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agropanifb.MenuCosecha
import com.example.agropanifb.MenuTrabajador
import com.google.android.material.bottomnavigation.BottomNavigationView

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        try {
            // Aplicar el listener para manejar los márgenes de las barras del sistema
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // Configurar el botón para redirigir a la actividad MenuCosecha
            val btnCosecha: Button = findViewById(R.id.btncosecha)
            btnCosecha.setOnClickListener {
                val intent = Intent(this, MenuCosecha::class.java)
                startActivity(intent)
            }

            // Configurar el botón para redirigir a la actividad MenuEmpleados
            val btnEmpleados: Button = findViewById(R.id.btnempleados)
            btnEmpleados.setOnClickListener {
                val intent = Intent(this, MenuTrabajador::class.java)
                startActivity(intent)
            }



        } catch (e: Exception) {
            // Manejar la excepción
            e.printStackTrace() // Imprimir el stack trace en caso de error
            Toast.makeText(this, "Ocurrió un error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
        // Aplicar ajustes de EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainmenu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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