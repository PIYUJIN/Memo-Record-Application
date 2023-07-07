package com.test.memo_record_application

import android.content.DialogInterface
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.test.memo_record_application.MemoClass.Companion.count
import com.test.memo_record_application.MemoClass.Companion.memoList
import com.test.memo_record_application.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {

    lateinit var activityMemoBinding: ActivityMemoBinding

    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(activityMemoBinding.root)

        activityMemoBinding.run {

            toolbarMemo.run {
                title = "메모 읽기"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.memo_menu)

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 메모 수정 버튼 클릭시 메모 수정 화면으로 이동
                        R.id.menuEdit -> {
                            var memoEditIntent = Intent(this@MemoActivity,MemoEditActivity::class.java)
                            memoEditIntent.putExtra("editPosition","$position")
                            startActivity(memoEditIntent)

                            false
                        }
                        // 메모 삭제 버튼 클릭시 삭제 확인 dialog 보여주기
                        R.id.menuDelete -> {
                            // 확인 클릭시 메모 삭제 후 이전 화면으로 돌아가기
                            // dialog 설정
                            val builder = AlertDialog.Builder(this@MemoActivity)
                            builder.setTitle("메모 삭제")
                            builder.setMessage("메모를 삭제 하겠습니까?")
                            builder.setNegativeButton("취소", null)
                            builder.setPositiveButton("삭제") { dialogInterface: DialogInterface, i: Int ->
                                DAO.deleteData(this@MemoActivity,position)
                                count--
                                for (idx in 0 until memoList.size) {
                                    if(memoList[idx].idx > position) {
                                        var obj = DAO.selectData(this@MemoActivity,idx+1)
                                        Log.d("lion","idx : ${obj.idx}")
                                        var index = obj.idx
                                        obj.idx = index-1
                                        Log.d("lion","update idx : ${obj.idx}")
                                        DAO.deleteUpdateData(this@MemoActivity,idx+1,obj)

//                                        var update = DAO.selectData(this@MemoActivity,idx)
//                                        Log.d("lion","update : ${update.idx}")
                                    }
                                }
                                finish()
                            }

                            builder.show()
                            false
                        }
                        else -> {
                            false
                        }
                    }
                }

                // back button 세팅
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }

    override fun onResume() {

        position = intent.getStringExtra("position")!!.toInt() + 1
//        Log.d("lion","$position")

        var memo = DAO.selectData(this@MemoActivity,position)
//        Log.d("lion","${memo.nameData}")

        activityMemoBinding.run {
            textViewMemoName.text = memo.nameData
            textViewMemoDate.text = memo.dateData
            textViewMemoContent.text = memo.contentData
        }

        super.onResume()
    }
}