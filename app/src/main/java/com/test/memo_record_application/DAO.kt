package com.test.memo_record_application

import android.content.ContentValues
import android.content.Context
import com.test.memo_record_application.MemoClass.Companion.memoList
import com.test.memo_record_application.databinding.ActivityMemoBinding

class DAO {
    companion object {
        // Create : 저장
        fun insertData(context: Context, data:MemoInfo ){
            // 컬럼이름과 데이터 설정 객체
            val contentValues = ContentValues()
            // 컬럼 이름, 값 지정
            contentValues.put("idx", data.idx)
            contentValues.put("nameData", data.nameData)
            contentValues.put("contentData", data.contentData)
            contentValues.put("dateData", data.dateData)

            val dbHelper = DBHelper(context)
            // 저장할 데이터를 가지고 있는 객체
            dbHelper.writableDatabase.insert("MemoTable", null, contentValues)
            dbHelper.close()
        }

        // Read Condition : 조건에 맞는 행 하나를 가져온다.
        fun selectData(context: Context, idx:Int):MemoInfo{

            val dbHelper = DBHelper(context)
            val selection = "idx = ?"
            val args = arrayOf("$idx")
            val cursor = dbHelper.writableDatabase.query("MemoTable", null, selection, args, null, null, null)

            cursor.moveToNext()

            // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다.
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("nameData")
            val idx3 = cursor.getColumnIndex("contentData")
            val idx4 = cursor.getColumnIndex("dateData")

            // 데이터를 가져온다.
            val idx = cursor.getInt(idx1)
            val nameData = cursor.getString(idx2)
            val contentData = cursor.getString(idx3)
            val dateData = cursor.getString(idx4)

            val memoInfoClass = MemoInfo(idx, nameData, contentData, dateData)

            dbHelper.close()

            return memoInfoClass
        }

        // Read All : 모든 행을 가져온다
        fun selectAllData(context: Context):MutableList<MemoInfo>{

            val dbHelper = DBHelper(context)

            val cursor = dbHelper.writableDatabase.query("MemoTable", null, null, null, null, null, null)

            while(cursor.moveToNext()){
                // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다.
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("nameData")
                val idx3 = cursor.getColumnIndex("contentData")
                val idx4 = cursor.getColumnIndex("dateData")

                // 데이터를 가져온다.
                val idx = cursor.getInt(idx1)
                val nameData = cursor.getString(idx2)
                val contentData = cursor.getString(idx3)
                val dateData = cursor.getString(idx4)

                val memoInfoClass = MemoInfo(idx, nameData, contentData, dateData)
                memoList.add(memoInfoClass)
            }

            dbHelper.close()

            return memoList
        }

        // Update : 조건에 맞는 행의 컬럼의 값 수정
        fun updateData(context:Context, obj:MemoInfo){
            // 컬럼과 값을 지정하는 ContentValues 생성한다.
            val cv = ContentValues()
            cv.put("idx",obj.idx)
            cv.put("nameData", obj.nameData)
            cv.put("contentData", obj.contentData)
            cv.put("dateData", obj.dateData)
            // 조건절
            val condition = "idx = ?"
            // ?에 들어갈 값
            val args = arrayOf("${obj.idx}")
            // 수정
            val dbHelper = DBHelper(context)
            // 테이블명, content values, 조건절, ?에 들어갈 값
            dbHelper.writableDatabase.update("MemoTable", cv, condition, args)
            dbHelper.close()
        }

        // Update : 조건에 맞는 행의 컬럼의 값 수정
        fun deleteUpdateData(context:Context, idx: Int, obj:MemoInfo){
            // 컬럼과 값을 지정하는 ContentValues 생성한다.
            val cv = ContentValues()
            cv.put("idx", obj.idx)
            cv.put("nameData", obj.nameData)
            cv.put("contentData", obj.contentData)
            cv.put("dateData", obj.dateData)
            // 조건절
            val condition = "idx = ?"
            // ?에 들어갈 값
            val args = arrayOf("${idx}")
            // 수정
            val dbHelper = DBHelper(context)
            // 테이블명, content values, 조건절, ?에 들어갈 값
            dbHelper.writableDatabase.update("MemoTable", cv, condition, args)
            dbHelper.close()
        }


        // Delete : 조건 맞는 행 삭제
        fun deleteData(context:Context, idx:Int){
            // 조건절
            val condition = "idx = ?"
            // ?에 들어갈 값
            val args = arrayOf("$idx")

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.delete("MemoTable", condition, args)
            dbHelper.close()
        }
    }
}