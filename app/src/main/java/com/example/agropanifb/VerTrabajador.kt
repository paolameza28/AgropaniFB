package com.example.agropanifb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agropanifb.Adapter.AdapterTrabajador
import com.example.agropanifb.Models.Trabajador
import com.example.agropanifb.databinding.ActivityVerTrabajadorBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerTrabajador : AppCompatActivity() {

    private lateinit var binding: ActivityVerTrabajadorBinding

    private lateinit var trabajadorList :ArrayList <Trabajador>

    private lateinit var trabajadorReciclerView :RecyclerView

    private lateinit var database : DatabaseReference
    private lateinit var adapterTrabajador: AdapterTrabajador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerTrabajadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trabajadorReciclerView =binding.rvTrabajador
        trabajadorReciclerView.layoutManager = LinearLayoutManager(this)
        trabajadorReciclerView.setHasFixedSize(true)

        trabajadorList = arrayListOf<Trabajador>()

        getTrabajador()




        }

    private fun getTrabajador() {
        database = FirebaseDatabase.getInstance().getReference("Trabajador")
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (trabajadorSnapshot in snapshot.children){
                        val trabajador =trabajadorSnapshot.getValue(Trabajador::class.java)
                        trabajadorList.add(trabajador!!)
                    }
                    adapterTrabajador = AdapterTrabajador(trabajadorList)
                    trabajadorReciclerView.adapter = adapterTrabajador
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
