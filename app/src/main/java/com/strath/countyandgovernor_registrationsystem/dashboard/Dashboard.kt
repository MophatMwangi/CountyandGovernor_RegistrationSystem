package com.strath.countyandgovernor_registrationsystem.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.recview.EdFarm
import com.strath.countyandgovernor_registrationsystem.recview.countyRecView
import com.strath.countyandgovernor_registrationsystem.recview.governorRecView
import com.strath.countyandgovernor_registrationsystem.views.Reports


class Dashboard : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)




        val card1 = findViewById<CardView>(R.id.cardCounty)
        val card2 = findViewById<CardView>(R.id.cardGovernor)
        val card3 = findViewById<CardView>(R.id.cardEdFarm)
        val card4 = findViewById<CardView>(R.id.cardReports)

        card1.setOnClickListener{
            val intent = Intent(this,countyRecView::class.java)
            startActivity(intent)
        }
        card2.setOnClickListener{
            val intent = Intent(this,governorRecView::class.java)
            startActivity(intent)
        }
        card3.setOnClickListener{
            val intent = Intent(this,EdFarm::class.java)
            startActivity(intent)
        }
        card4.setOnClickListener{
            val intent = Intent(this, Reports::class.java)
            startActivity(intent)
        }



    }

}







