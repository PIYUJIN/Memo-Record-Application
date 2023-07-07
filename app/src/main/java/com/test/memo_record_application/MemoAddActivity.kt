package com.test.memo_record_application

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.memo_record_application.MemoClass.Companion.count
import com.test.memo_record_application.databinding.ActivityMemoAddBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MemoAddActivity : AppCompatActivity() {

    lateinit var activityMemoAddBinding: ActivityMemoAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoAddBinding = ActivityMemoAddBinding.inflate(layoutInflater)
        setContentView(activityMemoAddBinding.root)

        activityMemoAddBinding.run {
            toolbarMemoAdd.run {
                title = "메모 추가"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.add_menu)

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuSave -> {
                            // 메모 저장 후 이전 화면으로 돌아가기

                            // 현재 시간을 년-월-일 양식으로 문자열 생성
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val now = sdf.format(Date())

                            var editName = editTextMemoName.text.toString()
                            var editContent = editTextMemoContent.text.toString()

                            DAO.insertData(this@MemoAddActivity,MemoInfo(count,editName,editContent,now))
                            count++

                            finish()
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
}