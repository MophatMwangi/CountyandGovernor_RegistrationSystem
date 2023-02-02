package com.strath.countyandgovernor_registrationsystem.recview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.strath.countyandgovernor_registrationsystem.Adapters.CountyAdapter
import com.strath.countyandgovernor_registrationsystem.Adapters.GovernorAdapter
import com.strath.countyandgovernor_registrationsystem.Model.CountyModel
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel

import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import com.strath.countyandgovernor_registrationsystem.views.CountyForm

public class countyRecView : AppCompatActivity() {

    companion object{
        lateinit var db: conncetionHelper
    }

    var countylist = ArrayList<CountyModel>()
    lateinit var adapter : RecyclerView.Adapter<CountyAdapter.CountyViewHolder>
    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_county_rec_view)

        val fb_btn = findViewById<FloatingActionButton>(R.id.floatingActionbuttonCounty)


        getCounty()



        fb_btn.setOnClickListener{

            Toast.makeText(this,"Hello morphy", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CountyForm::class.java)
            startActivity(intent)
        }


        val editSearchs = findViewById<EditText>(R.id.editSearchCounty)

        editSearchs.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged( s: CharSequence?, start: Int, count: Int,after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                var filteredCounty = ArrayList<CountyModel>()
                if(!editSearchs.text.isEmpty())
                {
                    for(i in 0..countylist.size-1)
                    {
                        if(countylist.get(i).county_name.toLowerCase().contains(s.toString().toLowerCase()))
                            filteredCounty.add(countylist[i])

                    }
                    adapter = CountyAdapter(applicationContext,filteredCounty)
                    rv = findViewById(R.id.recyclerViewCounty)
                    rv.adapter = adapter

                }else
                {
                    adapter = CountyAdapter(applicationContext,countylist)
                    rv= findViewById(R.id.recyclerViewCounty)
                    rv.adapter = adapter
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

    }

    private fun getCounty()
    {
         db = conncetionHelper(this, null)
         countylist = db.getCountyList(this)
         adapter = CountyAdapter(this,countylist)


         rv = findViewById(R.id.recyclerViewCounty)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

    }










}