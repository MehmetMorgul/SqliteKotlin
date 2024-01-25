package com.orucmorgul.sqlitewithkotlin

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import appHelper.Companion.IntentOlustur
import com.orucmorgul.sqlitewithkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adtxt: EditText
    lateinit var soyadtxt: EditText
    lateinit var yastxt: EditText
    lateinit var database: SQLiteDatabase
    lateinit var kaydetbtn: Button
    lateinit var ynlendirgnc: Button
    lateinit var silsayfabtn: Button
    lateinit var myDataBase:myDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adtxt=binding.adtxt
        soyadtxt=binding.soyadtxt
        yastxt=binding.yastxt
        kaydetbtn=binding.kaydetbtn
        silsayfabtn=binding.yonlendirsil
        ynlendirgnc=binding.ynlendirgnc
        myDataBase=myDataBase()


        myDataBase.VeriTabaniOlustur(this)

        kaydetbtn.setOnClickListener(){
            val ad = adtxt.text.toString()
            val soyad = soyadtxt.text.toString()
            val yas = yastxt.text.toString().toIntOrNull()

            if (yas != null) {
                myDataBase.ekleKullanici(ad,soyad,yas,this)
            }
        }
        ynlendirgnc.setOnClickListener(){
            IntentOlustur(this,MainActivity3::class.java)
        }
        silsayfabtn.setOnClickListener(){
            IntentOlustur(this,MainActivity2::class.java)
        }

    }

}