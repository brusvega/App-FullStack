package com.example.huertohogarapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class BuscarActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val productos = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        listView = findViewById(R.id.listViewResultados)

        val url = "http://10.40.109.56/huertohogar_api/listar_productos.php"




        cargarProductos(url)
    }

    private fun cargarProductos(url: String) {
        val queue = Volley.newRequestQueue(this)

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response: JSONArray ->
                productos.clear()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    val nombre = obj.getString("nombre")
                    val precio = obj.getDouble("precio")
                    productos.add("$nombre - $precio CLP")
                }

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productos)
                listView.adapter = adapter
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )

        queue.add(request)
    }
}
