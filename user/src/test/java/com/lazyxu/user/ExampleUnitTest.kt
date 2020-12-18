package com.lazyxu.user

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val intPlus: Int.(Int) -> Int= Int::plus
        println(intPlus.invoke(1, 1))
        println(intPlus(1, 2))

    }
   

}

