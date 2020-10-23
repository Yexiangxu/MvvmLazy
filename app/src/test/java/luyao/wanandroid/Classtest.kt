package luyao.wanandroid

import org.junit.Test

class Classtest {
    @Test
    fun addition_isCorrect() {
        var map: Map<String, Any>?=null
        if (map != null) {
            printMap(map)
        }
    }

    private fun printMap(map: Map<String, Any>) {
        println(message = map["ad_id"])

    }
}