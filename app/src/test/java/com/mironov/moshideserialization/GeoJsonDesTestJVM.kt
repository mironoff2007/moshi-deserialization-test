package com.mironov.moshideserialization

import com.mironov.moshideserialization.pojo.GeoJsonParser
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName


class GeoJsonDesTestJVM {

    private var time = 0L

    @get:Rule
    var testName: TestName = TestName()

    @Before
    fun before() {
        println()
    }

    @Test
    fun moshiTest() {

        val jsonString = GeoJsonParser.jsonString

        time = System.currentTimeMillis()

        val geoObject = GeoJsonParser.parse(jsonString)

        println(testName.methodName + ";" + (System.currentTimeMillis() - time))
        assert((geoObject.type?:"").isNotEmpty())
    }


}
