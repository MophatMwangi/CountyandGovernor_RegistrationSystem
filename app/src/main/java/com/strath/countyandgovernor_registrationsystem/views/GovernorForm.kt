package com.strath.countyandgovernor_registrationsystem.views

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import com.strath.countyandgovernor_registrationsystem.R
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.dashboard.Dashboard
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import com.strath.countyandgovernor_registrationsystem.databinding.ActivityGovernorFormBinding
import java.text.SimpleDateFormat

import java.util.*


class GovernorForm : AppCompatActivity() {


    private lateinit var binding: ActivityGovernorFormBinding
    private lateinit var fn_input: TextInputLayout
    private  lateinit var dob_input : TextInputLayout
    private lateinit var gender_input: TextInputLayout
    private lateinit var address_input: TextInputLayout
    private lateinit var city_input: TextInputLayout
    private lateinit var email_input: TextInputLayout
    private lateinit var pn_input: TextInputLayout
    private lateinit var county_input: TextInputLayout
    private lateinit var dob_txt_input: TextInputEditText
    private lateinit var addbutton: AppCompatButton


      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          //setContentView(R.layout.activity_governor_form)
          binding = ActivityGovernorFormBinding.inflate(layoutInflater)
          setContentView(binding.root)

          //call functions
          emailFocusListener()
          NameFocusListener()

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
          dob_txt_input = findViewById(R.id.dob_txt_in)

          val myCalendar = Calendar.getInstance()

          var calYear = myCalendar.get(Calendar.YEAR)
          var calMonth = myCalendar.get(Calendar.MONTH)
          var calDom = myCalendar.get(Calendar.DAY_OF_MONTH)
          val datePicker = DatePickerDialog.OnDateSetListener{ view,selectYear,selectMonth,selectDayofMonth ->
              /*calYear= year
              calMonth = month
              calDom = dayofMonth*/
              myCalendar.set(Calendar.YEAR,selectYear)
              myCalendar.set(Calendar.MONTH,selectMonth)
              myCalendar.set(Calendar.DAY_OF_MONTH,selectDayofMonth)


             val currentyear = Calendar.getInstance().get(Calendar.YEAR)
              val age = currentyear - myCalendar.get(Calendar.YEAR)
              val myage = 18
              if(myage == age)
              {
                  //addbutton.isEnabled = true
              }else if(age >= myage)
              {
                 // addbutton.isEnabled = false
                  updateLable(myCalendar)
                 // print("--------------")
                  //println(age)
                  Toast.makeText(this,"y:"+age,Toast.LENGTH_SHORT).show()

           }



          }

          dob_txt_input.setOnClickListener{
              DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
              Toast.makeText(this,"y:"+datePicker,Toast.LENGTH_SHORT).show()
          }
          fn_input = findViewById<TextInputLayout>(R.id.f_name)
          dob_input= findViewById<TextInputLayout>(R.id.dob)
          gender_input=findViewById<TextInputLayout>(R.id.gender)
          address_input= findViewById<TextInputLayout>(R.id.address)
          city_input= findViewById<TextInputLayout>(R.id.city)
          email_input= findViewById<TextInputLayout>(R.id.email)
          pn_input= findViewById<TextInputLayout>(R.id.pn)
          county_input= findViewById<TextInputLayout>(R.id.county)



           addbutton = findViewById(R.id.add_Governor)
          addbutton.setOnClickListener{
                 //validateEntry()
              // Toast.makeText(this,"Hello morphy", LENGTH_SHORT).show()
              //val db = conncetionHelper(this, null)
              /*var dbHelp = db.readableDatabase
              var username=fn_input.editText?.text.toString()
              var city=city_input.editText?.text.toString()
              var county=county_input.editText?.text.toString()
              val query="SELECT * FROM governor_form WHERE first_name='"+username+"' AND city='"+city+"' AND county='"+county+"'"
              val rs=dbHelp.rawQuery(query,null)
             // if(rs.isBeforeFirst){
                  var ad = AlertDialog.Builder(this)
                  ad.setTitle("Message")
                  ad.setMessage("Govername:${username} ,city:${city}  and county:${county} cannot be same!")
                  ad.setPositiveButton("Ok", null)
                  ad.show()
                  rs.close()
             // }
              //else {*/


                  val db = conncetionHelper(this, null)
                  val fname = fn_input.editText?.text.toString().toLowerCase()
                  val dob = dob_input.editText?.text.toString().toLowerCase()
                  val gender = gender_input.editText?.text.toString().toLowerCase()
                  val address = address_input.editText?.text.toString().toLowerCase()
                  val city = city_input.editText?.text.toString().toLowerCase()
                  val email = email_input.editText?.text.toString().toLowerCase()
                  val pn = pn_input.editText?.text.toString().toLowerCase()
                  val county = county_input.editText?.text.toString().toLowerCase()


                  db.addGovernor(fname, dob, gender, address, city, email, pn, county)

                  Toast.makeText(this, fname + " added to database", Toast.LENGTH_LONG).show()

                  clearGovernor()
             // }

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


    private fun emailFocusListener()
    {
        binding.emailContainer.setOnFocusChangeListener{_,focused ->
            if(!focused){
                binding.email.helperText= validEmail()
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun validEmail(): String?
    {
     val emailText = binding.emailContainer.text.toString()
        if(Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "invalid Email Address"
        }
        return null
    }
    private fun NameFocusListener()
    {
        binding.fnameContainer.setOnFocusChangeListener{_,focused ->
            if(!focused){
                binding.fName.helperText= validName()
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun validName(): String?
    {
     val nameText = binding.fnameContainer.text.toString()
        if(nameText.length < 20)
        {
            return "Minimum 20 Character full name"
        }
        if(nameText.matches(".*[a-z].*".toRegex()))
       {
        return "Minimum 20 Character full name"
       }
        return null
    }private fun AddressFocusListener()
    {
        binding.addressContainer.setOnFocusChangeListener{_,focused ->
            if(!focused){
                binding.address.helperText= validAddress()
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun validAddress(): String?
    {
     val addressText = binding.fnameContainer.text.toString()
        if(addressText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(addressText.length !=6)
       {
        return "Minimum 6 Character address"
       }
        return null
    }private fun cityFocusListener()
    {
        binding.cityContainer.setOnFocusChangeListener{_,focused ->
            if(!focused){
                binding.city.helperText= validCity()
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun validCity(): String?
    {
     val cityText = binding.cityContainer.text.toString()
        if(cityText.length < 10)
        {
            return "Minimum 10 Character city"
        }
        if(cityText.matches(".*[a-z].*".toRegex()))
        {
            return "Minimum 20 Character city"
        }
        return null
    }  private fun phoneFocusListener()
    {
        binding.pnContainer.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.pn.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String?
    {
        val phoneText = binding.pnContainer.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 9)
        {
            return "Must be 10 Digits"
        }
        return null
    }



}

