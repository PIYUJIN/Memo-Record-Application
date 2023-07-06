package com.test.memo_record_application

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.memo_record_application.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {

    lateinit var activityMemoEditBinding: ActivityMemoEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMemoEditBinding = ActivityMemoEditBinding.inflate(layoutInflater)
        setContentView(activityMemoEditBinding.root)

        activityMemoEditBinding.run {
            toolbarMemoEdit.run {
                title = "메모 수정"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.add_menu)

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menuSave -> {
                            // 메모 저장 후 이전 화면으로 돌아가기

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