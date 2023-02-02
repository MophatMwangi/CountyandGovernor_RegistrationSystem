package com.strath.countyandgovernor_registrationsystem.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.recview.countyRecView
import com.strath.countyandgovernor_registrationsystem.recview.governorRecView
import com.strath.countyandgovernor_registrationsystem.views.CountyForm
import com.strath.countyandgovernor_registrationsystem.views.GovernorForm

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        val card1 = findViewById<CardView>(R.id.cardCounty)
        val card2 = findViewById<CardView>(R.id.cardGovernor)

        card1.setOnClickListener{
            val intent = Intent(this,countyRecView::class.java)
            startActivity(intent)
        }
        card2.setOnClickListener{
            val intent = Intent(this,governorRecView::class.java)
            startActivity(intent)
        }



    }
}