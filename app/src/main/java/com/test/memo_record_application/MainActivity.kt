package com.test.memo_record_application

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.memo_record_application.MemoClass.Companion.count
import com.test.memo_record_application.MemoClass.Companion.memoList
import com.test.memo_record_application.databinding.ActivityMainBinding
import com.test.memo_record_application.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            recyclerViewMain.run {
                adapter = RecyclerViewAdapter()

                layoutManager = LinearLayoutManager(this@MainActivity)
            }

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

    override fun onResume() {

        memoList.clear()

        memoList = DAO.selectAllData(this)
        count = memoList.size+1

        var adapter = activityMainBinding.recyclerViewMain.adapter as RecyclerViewAdapter
        adapter.notifyDataSetChanged()

        super.onResume()
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {

            var textViewDate : TextView
            var textViewName : TextView

            init {
                textViewDate = rowBinding.textViewDate
                textViewName = rowBinding.textViewName

                rowBinding.root.setOnClickListener {
                    var memoIntent = Intent(this@MainActivity,MemoActivity::class.java)
                    memoIntent.putExtra("position", "$adapterPosition")
                    startActivity(memoIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            var rowBinding = RowBinding.inflate(layoutInflater)
            var viewHolder = ViewHolderClass(rowBinding)

            val params = ViewGroup.LayoutParams(
                // 가로 길이
                ViewGroup.LayoutParams.MATCH_PARENT,
                // 세로 길이
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            rowBinding.root.layoutParams = params

            return viewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewDate.text = memoList[position].dateData
            holder.textViewName.text = memoList[position].nameData
        }
    }
}