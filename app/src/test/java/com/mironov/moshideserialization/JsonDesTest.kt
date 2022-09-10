package com.mironov.moshideserialization

import com.google.gson.Gson
import com.mironov.moshideserialization.pojo.TestObjectGson
import com.mironov.moshideserialization.pojo.TestObjectMoshi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName


class JsonDesTest {

    private var time = 0L

    private val listObjectsStrings = mutableListOf<String>()

    private var jsonString = ""

    private val repeatCount = 5000
    private val innerClasses = 20

    @get:Rule
    var testName: TestName = TestName()

    val gson = Gson()

    init {
        gson.serializeNulls()
    }

    @Before
    fun before() {
        listObjectsStrings.clear()
        repeat(repeatCount) { ind ->
            var testObject = TestObjectGson()
            var currentObject = testObject
            repeat(innerClasses) {
                val innerObject = TestObjectGson(
                    field1 = "value $it",
                    field2 = 10 + it,
                    field3 = ind.rem(2) == 0,
                    field4 = 0.0 + +it,
                    field5 = 1000000L + it
                )
                currentObject.innerClass = innerObject
                currentObject = currentObject.innerClass!!
            }
            jsonString = gson.toJson(testObject)
            listObjectsStrings.add(jsonString)
        }
        println()
    }


    @Test
    fun moshiTest() {
        val  moshi =  Moshi.Builder().build()
        val jsonAdapter:JsonAdapter<TestObjectMoshi>  = moshi.adapter(TestObjectMoshi::class.java)

        var testObject: TestObjectMoshi? = null

        time = System.currentTimeMillis()

        listObjectsStrings.forEach {
            testObject = jsonAdapter.fromJson(it)!!
        }

        println(testName.methodName + " avg-" + (System.currentTimeMillis() - time) + "ms")
        assert(testObject?.innerClass != null)
    }


}
