package luyao.wanandroid

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.URLDecoder

/**
 * 协程是轻量级的线程
 */
class KotlinTest {
    fun json2map(str_json: String?): Map<String?, Any?>? {
        var res: Map<String?, Any?>? = null
        try {
            val gson = Gson()
            res = gson.fromJson(str_json, object : TypeToken<Map<String?, Any?>?>() {}.type)
        } catch (e: JsonSyntaxException) {
        }
        return res
    }

    @Test
    fun mapTest() {
        var params: List<String>
        params= listOf()
        println("map1=${listOf("")}")
        println("map2=${params}")

    }

    @Test
    fun restthread() {
        val time = System.currentTimeMillis()
        Thread { // 在后台启动一个新的协程 (新协程的生命周期只受整个应用程序的生命周期限制)
            Thread.sleep(1000L) // 非阻塞的等待 1 秒
            println("World!,time=${System.currentTimeMillis() - time}") // 在延迟后打印输出
        }
        for (i in 1..100_00) {
            print("Hello ") // 协程在等待,主线程还在继续
        }

//        Thread.sleep(2000L) // 阻塞主线程 2 秒
//        println("延迟两秒") // 协程在等待,主线程还在继续
    }

    @Test
    fun main() {
        GlobalScope.launch { // 在后台启动一个新的协程 (新协程的生命周期只受整个应用程序的生命周期限制)
            delay(1000L) // 非阻塞的等待 1 秒
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程在等待,主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒
        println("延迟两秒") // 协程在等待,主线程还在继续
    }

    @Test
    fun main1() {
        GlobalScope.launch { // 在后台启动一个新的协程
            delay(1000L) // 非阻塞的等待 1 秒
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程在等待,主线程还在继续

        runBlocking {
            Thread.sleep(2000L) // 阻塞主线程 2 秒
        }
        println("延迟两秒") // 协程在等待,主线程还在继续
    }

    @Test
    fun main2() = runBlocking {
        launch { // 在后台启动一个新的协程
            delay(1000L) // 非阻塞的等待 1 秒
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程在等待,主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒
        println("延迟两秒") // 协程在等待,主线程还在继续
    }

    @Test
    fun testApp() {
        getDistanceByStep(0)
        getDistanceByStep(0L)
    }

    fun getDistanceByStep(steps: Long): String {
        println("getDistanceByStep=${String.format("%.2f", steps * 0.6f / 1000)}")
        return String.format("%.2f", steps * 0.6f / 1000)
    }

    fun getCalorieByStep(steps: Long): String {
        println("getCalorieByStep=${String.format("%.1f", steps.toFloat() * 0.6f * 60f * 1.036f / 1000)}")
        return String.format("%.1f", steps.toFloat() * 0.6f * 60f * 1.036f / 1000)
    }

    @Test
    fun testurl() {
        println("params=${getMapByUri("/module_task/game?url_str=http%3a%2f%2ftest-b2.wonderful-app.com%3fstopmusic%3d1%26hideback%3d1")}")
    }

    fun getMapByUri(uri: String): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        val paramString = uri.substring(uri.indexOf("?") + 1)
        val paramArray = paramString.split("&")
        for (i in paramArray) {
            if (i.indexOf("=") != -1 && i.indexOf("_") != -1) {
                when (i.substring(i.indexOf("_") + 1, i.indexOf("="))) {
                    "str" -> map[i.substring(0, i.indexOf("_"))] = URLDecoder.decode(i.substring(i.indexOf("=") + 1), "UTF-8")
                    "int" -> map[i.substring(0, i.indexOf("_"))] = Integer.parseInt(i.substring(i.indexOf("=") + 1))
                    else -> map[i.substring(0, i.indexOf("_"))] = i.substring(i.indexOf("=") + 1)
                }
            }
        }
        return map
    }
}