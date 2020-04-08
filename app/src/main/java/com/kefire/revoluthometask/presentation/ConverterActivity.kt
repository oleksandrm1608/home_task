package com.kefire.revoluthometask.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kefire.revoluthometask.R
import java.math.BigDecimal
import java.math.RoundingMode

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_container, ConverterFragment.newInstance())
                .commit()
        }
    }
}
