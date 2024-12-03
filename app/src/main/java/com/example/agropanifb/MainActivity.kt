package com.example.agropanifb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agropanifb.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Configurar Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Firebase Auth
        auth = Firebase.auth

        // Programar el botón de login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.error = "Por favor ingrese un correo"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Por favor ingrese la contraseña"
                return@setOnClickListener
            }
            signIn(email, password)
        }

        // Programar el clic en "¿No estás registrado? Regístrate"
        binding.tvRegistrar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        // Programar el clic en "¿Olvidaste tu contraseña? Recuperar"
        binding.tvCambiar.setOnClickListener {
            val intent = Intent(this, RestaurarContrasena::class.java)
            startActivity(intent)
        }

    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()

                    // Redirigir a la actividad Menu
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)

                    // Finalizar MainActivity para que el usuario no pueda regresar con el botón de retroceso
                    finish()
                } else {
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
