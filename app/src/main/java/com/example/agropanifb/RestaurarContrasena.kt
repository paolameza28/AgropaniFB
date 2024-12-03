package com.example.agropanifb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RestaurarContrasena : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurar_contrasena)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Obtener las vistas
        val botonRestaurar: Button = findViewById(R.id.btnRestaurarContraseña)
        val correoARestaurar: EditText = findViewById(R.id.etCorreoRestaurar)

        botonRestaurar.setOnClickListener {
            val correo = correoARestaurar.text.toString()

            // Validar que el correo no esté vacío
            if (validarEmail(correo)) {
                try {
                    // Intentar enviar el correo de restauración
                    auth.sendPasswordResetEmail(correo)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Correo enviado...", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { exception ->
                            // En caso de error, mostrar el mensaje
                            Toast.makeText(this, "Error al enviar correo: ${exception.message}", Toast.LENGTH_SHORT).show()
                        }
                } catch (e: Exception) {
                    // Si ocurre una excepción, mostrar un mensaje
                    Toast.makeText(this, "Ocurrió un error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un correo válido...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para validar el correo
    private fun validarEmail(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
