package com.example.dodomainlayout.DataBase

import android.provider.BaseColumns

object dbCategory {
    const val tableName = "Category"

    const val id = "id"
    const val name = "name"
    const val description = "description"
    const val img = "img"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Category.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $tableName (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$id INTEGER,$name TEXT,$description TEXT,$img TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $tableName"
}