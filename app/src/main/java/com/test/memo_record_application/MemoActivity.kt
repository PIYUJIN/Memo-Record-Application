package com.test.memo_record_application

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.memo_record_application.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {

    lateinit var activityMemoBinding: ActivityMemoBinding
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
                        R.id.menuAdd -> {
                            var memoAddIntent = Intent(this@MemoActivity,MemoAddActivity::class.java)
                            startActivity(memoAddIntent)

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