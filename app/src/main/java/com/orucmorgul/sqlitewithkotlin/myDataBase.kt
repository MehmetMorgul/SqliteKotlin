package com.orucmorgul.sqlitewithkotlin

import android.R
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import appHelper.Companion.toastmsg

class myDataBase {
    lateinit var database: SQLiteDatabase
    fun VeriTabaniOlustur(context: Context): SQLiteDatabase {
        if (!::database.isInitialized) {
            database = context.openOrCreateDatabase("kullanici", AppCompatActivity.MODE_PRIVATE, null)
            database.execSQL("CREATE TABLE IF NOT EXISTS kullanici(id Integer PRIMARY KEY,name VARCHAR,lastname VARCHAR,yas INT)")
        }
        return database
    }
    fun ekleKullanici(ad: String,soyad: String,yas:Int,context: Context) {


        if (!ad.isNullOrBlank() && !soyad.isNullOrBlank() && yas != null) {
            val values = ContentValues()
            values.put("name", ad)
            values.put("lastname", soyad)
            values.put("yas", yas)

            database.insertOrThrow("kullanici", null, values)
            toastmsg(context, "Kullanıcı başarıyla eklendi.")

        } else {
            toastmsg(context, "Lütfen tüm alanları doldurun.")
        }
    }
    fun kullaniciSil(kullaniciId: Int,context: Context) {
        val selection = "id = ?"
        val selectionArgs = arrayOf(kullaniciId.toString())

        val cursor = database.query("kullanici", null, selection, selectionArgs, null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            database.delete("kullanici", selection, selectionArgs)
            toastmsg(context , "Kullanıcı başarıyla silindi.")

        } else {
            toastmsg(context, "Kullanıcı bulunamadı.")
        }

        cursor?.close()
    }
    fun kullaniciGuncelle(kullaniciId: String, ad: String, soyad: String, yas:String, context: Context) {
        val selection = "id = ?"
        val selectionArgs = arrayOf(kullaniciId.toString())

        val cursor = database.query("kullanici", null, selection, selectionArgs, null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            val values = ContentValues()


            if (!ad.isNullOrBlank()) {
                values.put("name", ad)
            }
            if (!soyad.isNullOrBlank()) {
                values.put("lastname", soyad)
            }
            if (!yas.isNullOrBlank()) {
                values.put("yas", yas)
            }

            if (values.size() > 0) {
                database.update("kullanici", values, selection, selectionArgs)
                toastmsg(context, "Kullanıcı başarıyla güncellendi.")

            } else {
                toastmsg(context, "Güncellenecek bilgi bulunamadı.")
            }
        } else {
            toastmsg(context, "Kullanıcı bulunamadı.")
        }

        cursor?.close()
    }

    @SuppressLint("Range")
    fun Listle(context: Context, listView: ListView) {
        val cursor = VeriTabaniOlustur(context).rawQuery("SELECT * FROM kullanici", null)

        if (cursor != null && cursor.moveToFirst()) {
            val userList = mutableListOf<String>()
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val lastName = cursor.getString(cursor.getColumnIndex("lastname"))
                val age = cursor.getInt(cursor.getColumnIndex("yas"))

                val userData = "ID: $id, Name: $name, Last Name: $lastName, Age: $age"

                userList.add(userData)
            } while (cursor.moveToNext())

            val adapter = ArrayAdapter<String>(context, R.layout.simple_list_item_1, userList)

            listView.adapter = adapter
        }
        cursor.close()
    }

}