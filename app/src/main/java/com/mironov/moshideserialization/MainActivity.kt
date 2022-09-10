package com.mironov.moshideserialization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mironov.moshideserialization.pojo.GeoJsonParser

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = GeoJsonParser.readJson(this)
        jsonString.toString()
    }


    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}