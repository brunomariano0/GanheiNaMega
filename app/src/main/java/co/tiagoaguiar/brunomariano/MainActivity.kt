package com.brunomariano.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)



        // buscar os objetos e ter a referencia deles
        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate )

        // banco de dados de preferencias
        val result = prefs.getString("result", null)
        if(result != null){
    txtResult.text ="Ultima aposta: $result"
        }

        btnGenerate.setOnClickListener {

            val text = editText.text.toString()
            numberGenerator(text, txtResult)
        }
    }

    private fun numberGenerator(text:String, txtResult: TextView) = if (text.isNotEmpty()){
        val qtd = text.toInt() // convert string para inteiro

        //validar se o campo informado é entre 6 e 15
        if (qtd >=6 && qtd <=15){

            val numbers = mutableSetOf<Int>()
            val random = java.util.Random()
            while(true){
                val number = random.nextInt(60) // 0.59
                numbers.add(number + 1)
                if (numbers.size == qtd){
                    break
                }
            }

            txtResult.text = numbers.joinToString("-")
            



            val editor = prefs.edit()
            editor.putString("result", txtResult.text.toString())
            editor.apply()




        }else{
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_SHORT).show()
        }

    }else {
        Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_SHORT).show()
    }

}