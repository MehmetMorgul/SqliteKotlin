package com.orucmorgul.sqlitewithkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.orucmorgul.sqlitewithkotlin.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    lateinit var idtxt: EditText
    lateinit var adtxt2: EditText
    lateinit var soyadtxt2: EditText
    lateinit var yastxt2: EditText
    lateinit var listelebtn: Button
    lateinit var guncellebtn: Button
    lateinit var myDataBase:myDataBase
    lateinit var liste: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMain3Binding.inflate(layoutInflater)
        var view =binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        idtxt=binding.idtxt
        adtxt2=binding.adtxt2
        soyadtxt2=binding.soyadtxt2
        yastxt2=binding.yastxt2
        listelebtn=binding.listelebtn
        guncellebtn=binding.guncellebtn
        liste=binding.liste
        myDataBase=myDataBase()

        listelebtn.setOnClickListener() {
            myDataBase.Listle(this, liste)
        }
        guncellebtn.setOnClickListener(){
            val id = idtxt.text.toString()
            val ad = adtxt2.text.toString()
            val soyad = soyadtxt2.text.toString()
            val yas = yastxt2.text.toString()
            myDataBase.kullaniciGuncelle(id,ad,soyad,yas,this)
            myDataBase.Listle(this, liste)
        }

    }
}