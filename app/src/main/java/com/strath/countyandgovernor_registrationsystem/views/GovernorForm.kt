package com.strath.countyandgovernor_registrationsystem.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.UpdateLayout
import android.widget.ArrayAdapter
import com.strath.countyandgovernor_registrationsystem.R
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import java.text.SimpleDateFormat
import java.util.*


class GovernorForm : AppCompatActivity() {



    private lateinit var fn_input: TextInputLayout
    private  lateinit var dob_input : TextInputLayout
    private lateinit var gender_input: TextInputLayout
    private lateinit var address_input: TextInputLayout
    private lateinit var city_input: TextInputLayout
    private lateinit var email_input: TextInputLayout
    private lateinit var pn_input: TextInputLayout
    private lateinit var county_input: TextInputLayout

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_governor_form)

          //dropdown for county
          val county_category = listOf(
              "nairobi",
              "kiambu",
              "muranga",
              "Nyandarua",
              "Kirinyaga",
              "muranga",
              "nyeri",
              "turkana",
              "west pokot",
              "samburu",
              "trans nzoia",
              "uasin gishu",
              "elgeyo-marakwet",
              "nandi",
              "baringo",
              "laikipia",
              "nakuru",
              "narok",
              "kajiado",
              "kericho",
              "Bomet",
              "kisumu",
              "homabay",
              "migori",
              "kisii",
              "nyamira",
              "siaya",
              "vihiga",
              "busia",
              "kericho",
              "bomet",
              "kakamega",
              "marsabit",
              "isiolo",
              "meru",
              "tharaka-nithi",
              "embu",
              "kitui",
              "machakos",
              "makueni",
              "garissa",
              "wajir",
              "mandera",
              "kilifi",
              "kwale",
              "Lamu",
              "Mombasa",
              "taita taveta",
              "tana river"
          )
          val county_adapter = ArrayAdapter(this, R.layout.county_list, county_category)
          val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.county_type)
          autoCompleteTextView.setAdapter(county_adapter)

          //dropdown for gender
          val gender_category = listOf("male", "female")
          val genderAdapter = ArrayAdapter(this, R.layout.gender_list, gender_category)
          val autocompleteTextView = findViewById<AutoCompleteTextView>(R.id.gender_type)
          autocompleteTextView.setAdapter(genderAdapter)

         dob_input = findViewById(R.id.dob)

          val myCalendar = Calendar.getInstance()
          val datePicker = DatePickerDialog.OnDateSetListener{ view,year,month,dayofMonth ->
              myCalendar.set(Calendar.YEAR,year)
              myCalendar.set(Calendar.MONTH,month)
              myCalendar.set(Calendar.DAY_OF_MONTH,dayofMonth)
              updateLable(myCalendar)

          }

          dob_input.setOnClickListener{
              DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
           Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
          }






      }

   fun  updateLable(myCalendar: Calendar)
   {
    val myformat = "dd-mm-yyyy"
       val sdf = SimpleDateFormat(myformat)
       dob_input.editText?.setText(sdf.format(myCalendar.time))

   }

    fun clearGovernor()
    {
         fn_input.editText?.setText("")
         dob_input.editText?.setText("")
         gender_input.editText?.setText("")
         address_input.editText?.setText("")
         city_input.editText?.setText("")
         email_input.editText?.setText("")
         pn_input.editText?.setText("")
         county_input.editText?.setText("")
    }



}

