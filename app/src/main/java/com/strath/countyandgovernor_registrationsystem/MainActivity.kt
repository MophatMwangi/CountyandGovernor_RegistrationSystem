package com.strath.countyandgovernor_registrationsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.strath.countyandgovernor_registrationsystem.dashboard.Dashboard
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import com.strath.countyandgovernor_registrationsystem.views.Login
import com.strath.countyandgovernor_registrationsystem.views.Sign

class MainActivity : AppCompatActivity() {
    companion object {
        private lateinit var db: conncetionHelper
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loginbutton = findViewById<AppCompatButton>(R.id.login)

        loginbutton.setOnClickListener{
            Toast.makeText(this,"Welcome", Toast.LENGTH_LONG).show()
            val intent = Intent(this , Login::class.java)
            startActivity(intent)
            db = conncetionHelper(this,null)
        }

        val signup_button = findViewById<AppCompatButton>(R.id.sign_up)

        signup_button.setOnClickListener{
            Toast.makeText(this,"Welcome", Toast.LENGTH_LONG).show()
            val intent = Intent(this , Sign::class.java)
            startActivity(intent)
            db = conncetionHelper(this,null)
        }



    }


}