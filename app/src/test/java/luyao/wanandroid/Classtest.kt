package luyao.wanandroid

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.junit.Test
import kotlin.coroutines.CoroutineContext


class Classtest {
    class AbstractStrategy : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = SupervisorJob()

        @Test
        fun addition_isCorrect() {
            var test = null
            println(test ?: "")
//            var test = AbstractStrategy()
//            test.launch {
//                try {
//                    while (true) {
//                        delay(500)
//                        println("test")
//                    }
//                } finally {
//
//                }
//            }

            Thread.sleep(2000)
            Thread.sleep(2000)

        }

        // 源代码
        fun test(a: Int, b: Int): Int {
            return a + b
        }

        fun sum(num1: Int, num2: Int): Int {
            return num1 + num2
        }// 调用

        //        test(10,sum(3,5))// 结果为：18//
        fun test(a: Int, b: (num1: Int, num2: Int) -> Int): Int {
            return a + b.invoke(3, 5)
        }// 调用test(10,{ num1: Int, num2: Int ->  num1 + num2 })
    }
    enum class Number(var num: Int){

        ONE(1),
        TWO(2)

    }
    @Test
    fun testEnum(){
       println("输出=" + LanguageEnum.LANGUAGE_zh.language)
        println("输出=" + Number.ONE.num)
    }
}
enum class LanguageEnum
/**构造函数，枚举类型只能为私有 */(  //自定义方法
        //俄语
        val language //自定义属性
        : String) {
    LANGUAGE("language"),  //语用于SharedPreferences存储的Key值
    LANGUAGE_zh("zh"),  //中文，用于SharedPreferences存储的Value值
    LANGUAGE_en("en"),  //英语
    LANGUAGE_es("es"),  //西语
    LANGUAGE_fr("fr"),  //法语
    LANGUAGE_ar("ar"),  //阿语
    LANGUAGE_ru("ru");

}