package com.example.dodomainlayout.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbCategoryHelper(context: Context) : SQLiteOpenHelper(context, dbCategory.DATABASE_NAME, null, dbCategory.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(dbCategory.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(dbCategory.SQL_DELETE_TABLE)
        onCreate(db)
    }
}