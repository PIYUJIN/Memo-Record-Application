package com.test.memo_record_application

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.memo_record_application.databinding.ActivityMemoAddBinding

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