package com.strath.countyandgovernor_registrationsystem.views


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper


class CountyForm : AppCompatActivity() {

     private lateinit var cn_input: TextInputLayout
     private lateinit var pop_input: TextInputLayout
     private lateinit var area_input: TextInputLayout
     private lateinit var not_input: TextInputLayout
     private lateinit var cno_input: TextInputLayout
     private lateinit var nos_input: TextInputLayout
     private lateinit var noh_input: TextInputLayout
     private lateinit var governor_input: TextInputLayout

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_county_form)

        val items = listOf("nairobi","kiambu","muranga","Nyandarua","Kirinyaga","muranga","nyeri","turkana","west pokot","samburu","trans nzoia","uasin gishu","elgeyo-marakwet","nandi","baringo","laikipia","nakuru","narok","kajiado","kericho","Bomet","kisumu","homabay","migori","kisii","nyamira","siaya","vihiga","busia","kericho","bomet","kakamega","marsabit","isiolo","meru","tharaka-nithi","embu","kitui","machakos","makueni","garissa","wajir","mandera","kilifi","kwale","Lamu","Mombasa","taita taveta","tana river")
        val adapter = ArrayAdapter(this,R.layout.county_list,items)
        val   autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.countyname_type)
        autoCompleteTextView.setAdapter(adapter)

        //input textfield
        cn_input = findViewById<TextInputLayout>(R.id.county_name)
         pop_input= findViewById<TextInputLayout>(R.id.population)
         area_input=findViewById<TextInputLayout>(R.id.area)
         not_input= findViewById<TextInputLayout>(R.id.no_towns)
         cno_input= findViewById<TextInputLayout>(R.id.county_no)
         nos_input= findViewById<TextInputLayout>(R.id.no_school)
         noh_input= findViewById<TextInputLayout>(R.id.no_hospitals)
         governor_input= findViewById<TextInputLayout>(R.id.governors)





              //button
        val addbutton = findViewById<AppCompatButton>(R.id.add_county)
        addbutton.setOnClickListener{
           // Toast.makeText(this,"Hello morphy", LENGTH_SHORT).show()
            val db = conncetionHelper(this, null)
           val name = cn_input.editText?.text.toString()
           val pop  =pop_input.editText?.text.toString()
           val area = area_input.editText?.text.toString()
           val not = not_input.editText?.text.toString()
           val cno = cno_input.editText?.text.toString()
           val nos = nos_input.editText?.text.toString()
           val noh = noh_input.editText?.text.toString()
           val governors = governor_input.editText?.text.toString()


                         db.addCounty(name,pop,area,not,cno,nos,noh,governors)

            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            clearCounty()

        }





    }

                 private fun insertDataToDatabase()
             {
           val add_cn = cn_input.editText?.text.toString()
           val add_pop = pop_input.editText?.text.toString()
           val add_area = area_input.editText?.text.toString()
           val add_not = not_input.editText?.text.toString()
           val add_nos = nos_input.editText?.text.toString()
           val add_cnoh = noh_input.editText?.text.toString()
           val add_cno = cno_input.editText?.text.toString()
           val add_governor = governor_input.editText?.text.toString()
             }


            fun clearCounty()
          {
              cn_input.editText?.setText("")
              pop_input.editText?.setText("")
              area_input.editText?.setText("")
              not_input.editText?.setText("")
              nos_input.editText?.setText("")
              noh_input.editText?.setText("")
              cno_input.editText?.setText("")
              governor_input.editText?.setText("")
         }




}