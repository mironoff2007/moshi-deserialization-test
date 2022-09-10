package com.mironov.moshideserialization

import androidx.test.platform.app.InstrumentationRegistry
import com.mironov.moshideserialization.pojo.GeoJsonMoshi
import com.mironov.moshideserialization.pojo.GeoJsonParser
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName


class GeoJsonDesTest {

    private var time = 0L

    @get:Rule
    var testName: TestName = TestName()

    @Before
    fun before() {
        println()
    }

    @Test
    fun moshiTest() {

        val context = InstrumentationRegistry.getInstrumentation().context
        val jsonString = GeoJsonParser.readJson(context)

        time = System.currentTimeMillis()

        val geoObject = GeoJsonParser.parse(jsonString)

        println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        assert((geoObject.type?:"").isNotEmpty())
    }


}
