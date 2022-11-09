package com.example.myapplication5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val list = mutableListOf<Todo>()
    private lateinit var adapter: RecyclerAdapter
    private val dbHelper = DBHelper(this)

    companion object{
        val EDIT_TEXT_KEY = "EDIT_TEXT"
        val EXTRA_KEY = "EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val editText = findViewById<EditText>(R.id.editText)
            editText.setText(savedInstanceState.getString(EDIT_TEXT_KEY))
        }


        // val STATE_KEY = "STATE"

        // override fun onSaveInstanceState(outState: Bundle) {
        //     super.onSaveInstanceState(outState)

        //     val editText = findViewById<EditText>(R.id.editText)

        // сохраняем текущие значения в state
        //    state.text = editText.text
        //    state.counter = // ...

        //         outState.putSerializable(STATE_KEY, state)
        //  }

        adapter = RecyclerAdapter(list) {
            // адаптеру передали обработчик удаления элемента
            //TODO: удалять из базы тоже
            dbHelper.remove(list[it].id)
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }

//        dbHelper.add(editText.text.toString())
        val list1 = dbHelper.getAll()
        list.addAll(list1)


// создаём инстанс адаптера, отдаём ему список

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
// у нас будет линейный список
        recyclerView.layoutManager = LinearLayoutManager(this)
// прикручиваем адаптер к RecyclerView
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)

        val buttonAdd = findViewById<Button>(R.id.button)
        buttonAdd.setOnClickListener {


          val id = dbHelper.add(editText.text.toString())
            list.add(Todo(id , editText.text.toString()))
            adapter.notifyItemInserted(list.lastIndex)

        }


    }

   // override fun onSaveInstanceState(outState: Bundle) {
     //   val editText = findViewById<EditText>(R.id.editText)
      //  outState.putString(EDIT_TEXT_KEY, editText.text.toString())
      //  super.onSaveInstanceState(outState)
   // }



}
