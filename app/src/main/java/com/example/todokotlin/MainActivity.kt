package com.example.todokotlin

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var item: EditText
    private lateinit var list: ListView
    private lateinit var btn: Button

    private var todoList = ArrayList<String>()
    private val fileHelper = TodoHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize UI elements after setContentView
        item = findViewById(R.id.editText)
        list = findViewById(R.id.list)
        btn = findViewById(R.id.addtodobtn)

        // Read data from file
        todoList = fileHelper.readData(context = this)

        // Setup ArrayAdapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoList)
        list.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Button Click Listener
        btn.setOnClickListener {
            val text = item.text.toString()
            if (text.isNotEmpty()) {
                todoList.add(text)
                item.setText("")
                fileHelper.writeData(todoList, context = this)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Enter a task!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
