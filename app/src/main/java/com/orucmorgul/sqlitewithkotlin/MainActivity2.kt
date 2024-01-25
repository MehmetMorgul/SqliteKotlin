package com.orucmorgul.sqlitewithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.orucmorgul.sqlitewithkotlin.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var liste: ListView
    lateinit var islemidtxt2: EditText
    lateinit var silbtn: Button
    lateinit var myDataBase:myDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMain2Binding.inflate(layoutInflater)
        var view =binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)
        liste=binding.liste
        liste=binding.liste
        silbtn=binding.silbtn
        myDataBase=myDataBase()

        myDataBase.Listle(this, liste)

        silbtn.setOnClickListener(){
            val id =islemidtxt2.text.toString().toIntOrNull()
            if (id != null) {
                myDataBase.kullaniciSil(id,this)
            }
            myDataBase.Listle(this, liste)
        }


    }
}