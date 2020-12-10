package luyao.wanandroid

import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class Classtest {
    @Test
    fun addition_isCorrect() {

    }


}

public inline fun CharSequence.sumBy(selector: (Char) -> Int): Int {
    var sum: Int = 0
    for (element in this) {
        sum += selector(element)
    }
    return sum
}




interface ServerApi {
    fun login(username: String, password: String)
    fun register(username: String, password: String)
}

class Retrofit : ServerApi {

    override fun login(username: String, password: String) {
        println("login: username = $username, password = $password")
    }

    override fun register(username: String, password: String) {
        println("register: username = $username, password = $password")
    }
}

class RemoteRepository(retrofit: Retrofit) : ServerApi by retrofit{

    override fun register(username: String, password: String) {
        println("register in RemoteRepository.")
    }
}