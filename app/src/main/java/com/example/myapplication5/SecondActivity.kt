package com.example.myapplication5

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication5.SecondActivity.Companion.EXTRA_KEY

class SecondActivity : AppCompatActivity() {

    companion object {
       const val EXTRA_KEY = "EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // val intent = getIntent()
        val s = intent.getStringExtra(MainActivity.EXTRA_KEY)

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = s
    }
}