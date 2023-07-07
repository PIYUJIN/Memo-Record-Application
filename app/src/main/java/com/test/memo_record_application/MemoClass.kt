package com.test.memo_record_application

class MemoClass {
    companion object {
        var memoList = mutableListOf<MemoInfo>()
        var count = 1
    }
}

data class MemoInfo(var idx:Int,
                       var nameData:String,
                       var contentData :String,
                       var dateData:String)