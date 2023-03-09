package com.strath.countyandgovernor_registrationsystem.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper

class Reports : AppCompatActivity() {

    companion object {
        lateinit var db: conncetionHelper
    }

    private lateinit var cn_out: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        val db = conncetionHelper(this, null)
        cn_out = findViewById(R.id.hospital_txt)
        val rt = db.getCList()

        cn_out.setText(rt.toString())


    }
}