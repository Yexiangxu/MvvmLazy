package luyao.wanandroid

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.concurrent.thread

/**
 * 协程是轻量级的线程
 */
class KotlinTest {
    @Test
    fun restthread() {
        val time=System.currentTimeMillis()
        Thread { // 在后台启动一个新的协程 (新协程的生命周期只受整个应用程序的生命周期限制)
            Thread.sleep(1000L) // 非阻塞的等待 1 秒
            println("World!,time=${System.currentTimeMillis()-time}") // 在延迟后打印输出
        }
        for (i in 1..100_00){
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
    fun main1(){
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
    fun main2() = runBlocking{
        launch { // 在后台启动一个新的协程
            delay(1000L) // 非阻塞的等待 1 秒
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程在等待,主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒
        println("延迟两秒") // 协程在等待,主线程还在继续
    }
}