package com.example.dodomainlayout.DataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.dodomainlayout.network.model.Category
import com.example.dodomainlayout.network.model.CategoryArray

class dbCategoryManager (context: Context) {
    var dbHelper = dbCategoryHelper(context)
    var db: SQLiteDatabase? = null

    fun OpenDb()
    {
        db = dbHelper?.writableDatabase
    }

    fun InsertToDb(cat: Category)
    {
        val values = ContentValues().apply {
            put(dbCategory.id, cat.idCategory)
            put(dbCategory.name, cat.strCategory)
            put(dbCategory.description, cat.strCategoryDescription)
            put(dbCategory.img, cat.strCategoryThumb)
        }
        db?.insert("Category", null, values)
    }
    @SuppressLint("Range")
    fun ReadData() : CategoryArray {
        val cursor = db?.query("Category", null, null, null, null, null, null)
        val catList = ArrayList<Category>()
        while(cursor?.moveToNext()!!)
        {
            var cat = Category(0, "", "", "")
            cat?.idCategory =
                cursor?.getInt(cursor.getColumnIndex(dbCategory.id))
            cat?.strCategory =
                cursor?.getString(cursor.getColumnIndex(dbCategory.name)).toString()
            cat?.strCategoryDescription =
                cursor?.getString(cursor.getColumnIndex(dbCategory.description)).toString()
            cat?.strCategoryThumb =
                cursor?.getString(cursor.getColumnIndex(dbCategory.img)).toString()
            catList.add(cat)
        }
        cursor.close()
        return CategoryArray(catList.toTypedArray())
    }
    fun delete()
    {
        db?.delete("Category", null, null)
    }

    fun CloseDb(){
        dbHelper?.close()
    }
}