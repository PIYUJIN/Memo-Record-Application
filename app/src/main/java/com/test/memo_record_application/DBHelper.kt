package com.test.memo_record_application

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Memo.db", null, 1) {
    override fun onCreate(sqlistDatabase: SQLiteDatabase?) {
        val sql = """create table MemoTable
            (idx integer primary key not null,
            nameData text not null,
            contentData text not null,
            dateData date not null)
        """.trimIndent()

        // 쿼리문 수행
        sqlistDatabase?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

}