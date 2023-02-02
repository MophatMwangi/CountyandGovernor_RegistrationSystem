package com.strath.countyandgovernor_registrationsystem.recview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.strath.countyandgovernor_registrationsystem.Adapters.CountyAdapter
import com.strath.countyandgovernor_registrationsystem.Adapters.GovernorAdapter
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import com.strath.countyandgovernor_registrationsystem.views.CountyForm
import com.strath.countyandgovernor_registrationsystem.views.GovernorForm

class governorRecView : AppCompatActivity() {

    companion object {
        lateinit var db: conncetionHelper
    }

    var governorlist = ArrayList<GovernorModel>()
    lateinit var adapter : RecyclerView.Adapter<GovernorAdapter.ViewHolder>
    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_governor_rec_view)

        val fb_btn = findViewById<FloatingActionButton>(R.id.floatingActionbuttonGovernor)

        getGovernor()



        fb_btn.setOnClickListener{

            Toast.makeText(this,"Hello morphy", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, GovernorForm::class.java)
            startActivity(intent)

        }

        val editSearch = findViewById<EditText>(R.id.editSearch)

        editSearch.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged( s: CharSequence?, start: Int, count: Int,after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                var filteredGovernor = ArrayList<GovernorModel>()
                if(!editSearch.text.isEmpty())
                {
                    for(i in 0..governorlist.size-1)
                    {
                     if(governorlist.get(i).fname.toLowerCase().contains(s.toString().toLowerCase()))
                       filteredGovernor.add(governorlist[i])

                    }
                     adapter = GovernorAdapter(applicationContext,filteredGovernor)
                     rv = findViewById(R.id.recyclerViewGovernor)
                    rv.adapter = adapter

                }else
                {
                     adapter = GovernorAdapter(applicationContext,governorlist)
                     rv= findViewById(R.id.recyclerViewGovernor)
                    rv.adapter = adapter
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


        }





    private fun getGovernor()
    {
        db = conncetionHelper(this, null)
        governorlist = db.getGovernorList(this)
         adapter = GovernorAdapter(this,governorlist)


        rv = findViewById(R.id.recyclerViewGovernor)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

    }


}