package com.example.huertohogarapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val opciones = listOf(
        "Buscar productos",
        "Agregar producto",
        "Editar o eliminar producto",
        "Modificar precios",
        "Cerrar sesión"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        listView = findViewById(R.id.listViewOpciones)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            opciones
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this, BuscarActivity::class.java))
                1 -> startActivity(Intent(this, AgregarActivity::class.java))
                2 -> startActivity(Intent(this, EditarActivity::class.java))
                3 -> Toast.makeText(this, "Función de modificar precios", Toast.LENGTH_SHORT).show()
                4 -> {
                    Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
