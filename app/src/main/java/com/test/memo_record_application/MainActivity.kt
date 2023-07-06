package com.test.memo_record_application

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.memo_record_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            toolbarMain.run {
                // tool bar 세팅
                title = "메모앱"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 추가 버튼 클릭시 메모 추가 화면으로 이동
                        R.id.menuAdd -> {
                            var addIntent = Intent(this@MainActivity,MemoAddActivity::class.java)
                            startActivity(addIntent)

                            false
                        }

                        else -> {
                            false
                        }
                    }
                }
            }
        }
    }
}