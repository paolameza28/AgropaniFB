package com.example.agropanifb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Salir : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salir)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Manejar clic en el botón "Cerrar Sesión"
        findViewById<Button>(R.id.btnCerrarSesion).setOnClickListener {
            auth.signOut() // Cerrar sesión de Firebase
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finalizar la actividad actual para evitar regresar con el botón de retroceso
        }

        // Configuración para ajustar los márgenes con los insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}