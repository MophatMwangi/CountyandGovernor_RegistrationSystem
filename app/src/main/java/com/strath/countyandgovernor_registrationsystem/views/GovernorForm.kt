package com.strath.countyandgovernor_registrationsystem.views

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.strath.countyandgovernor_registrationsystem.R
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.Model.CountyDetailsModel
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel
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
    private lateinit var code_input: TextInputLayout
    private lateinit var area_input: TextInputLayout
    private lateinit var addbutton: AppCompatButton
     var age = 0;

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          //setContentView(R.layout.activity_governor_form)
          binding = ActivityGovernorFormBinding.inflate(layoutInflater)
          setContentView(binding.root)

          //call functions

          //county details
          dropDown()
          checkValidation()
          //insert counties, area and code
          insertCounties()

          //dropdown for gender
          val gender_category = listOf("male", "female")
          val genderAdapter = ArrayAdapter(this, R.layout.gender_list, gender_category)
          val autocompleteTextView = findViewById<AutoCompleteTextView>(R.id.gender_type)
          autocompleteTextView.setAdapter(genderAdapter)

          dob_input = findViewById(R.id.dob)
          dob_txt_input = findViewById(R.id.dobContainer)

          val myCalendar = Calendar.getInstance()

          var calYear = myCalendar.get(Calendar.YEAR)
          var calMonth = myCalendar.get(Calendar.MONTH)
          var calDom = myCalendar.get(Calendar.DAY_OF_MONTH)
          val datePicker = DatePickerDialog.OnDateSetListener{ view,selectYear,selectMonth,selectDayofMonth ->

              myCalendar.set(Calendar.YEAR,selectYear)
              myCalendar.set(Calendar.MONTH,selectMonth)
              myCalendar.set(Calendar.DAY_OF_MONTH,selectDayofMonth)


             val currentyear = Calendar.getInstance().get(Calendar.YEAR)
               age = currentyear - myCalendar.get(Calendar.YEAR)
              val myage = 18
              if(myage == age)
              {

              }else if(age >= myage)
              {

                  updateLable(myCalendar)
                  Toast.makeText(this,"y:"+age,Toast.LENGTH_SHORT).show()

           }



          }

          dob_txt_input.setOnClickListener{
              DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
              //Toast.makeText(this,"y:"+datePicker,Toast.LENGTH_SHORT).show()
          }

          fn_input = findViewById(R.id.fname)
          dob_input= findViewById(R.id.dob)
          gender_input=findViewById(R.id.gender)
          address_input= findViewById(R.id.address)
          city_input= findViewById(R.id.city)
          email_input= findViewById(R.id.email)
          pn_input= findViewById(R.id.pn)
          county_input= findViewById(R.id.county)
          code_input= findViewById(R.id.Code)
          area_input= findViewById(R.id.Area)



          addbutton = findViewById(R.id.add_Governor)
          addbutton.setOnClickListener{

              if (TextUtils.isEmpty(binding.codeContainer.text.toString())) {
                  binding.Code.isErrorEnabled = false
                  binding.Code.error = "enter code"
              } else if (TextUtils.isEmpty(binding.countyAreaContainer.text.toString())) {
                  binding.Area.isErrorEnabled = false
                  binding.Area.error = "enter area"
              } else if (TextUtils.isEmpty(binding.fnameContainer.text.toString())) {
                  binding.fname.isErrorEnabled = false
                  binding.fname.error = "enter full name"
              }else if (TextUtils.isEmpty(binding.dobContainer.text.toString())) {
                  binding.dob.isErrorEnabled = false
                  binding.dob.error = "enter date of birth"
              }else if (TextUtils.isEmpty(binding.genderType.text.toString())) {
                  binding.gender.isErrorEnabled = false
                  binding.gender.error = "enter gender"
              } else if (TextUtils.isEmpty(binding.addressContainer.text.toString())) {
                  binding.address.isErrorEnabled = false
                  binding.address.error = "enter address"
              } else if (TextUtils.isEmpty(binding.cityContainer.text.toString())) {
                  binding.city.isErrorEnabled = false
                  binding.city.error = "enter city"
              } else if (TextUtils.isEmpty(binding.emailContainer.text.toString())) {
                  binding.email.isErrorEnabled = false
                  binding.email.error = "enter email"
              } else if (TextUtils.isEmpty(binding.pnContainer.text.toString())) {
                  binding.pn.isErrorEnabled = false
                  binding.pn.error = "enter phone"

              }else{
                  val db = conncetionHelper(this, null)
                  val fname = fn_input.editText?.text.toString().toLowerCase()
                  val dob = dob_input.editText?.text.toString().toLowerCase()
                  val gender = gender_input.editText?.text.toString().toLowerCase()
                  val address = address_input.editText?.text.toString().toLowerCase()
                  val city = city_input.editText?.text.toString().toLowerCase()
                  val email = email_input.editText?.text.toString().toLowerCase()
                  val pn = pn_input.editText?.text.toString().toLowerCase()
                  val county = county_input.editText?.text.toString().toLowerCase()
                  val countyCode = code_input.editText?.text.toString().toLowerCase()
                  val countyArea = area_input.editText?.text.toString().toLowerCase()


                  val savedata= db.addGovernor(fname, dob,age.toString(), gender, address, city, email, pn, county,countyArea,countyCode)

                  if (savedata == true){
                      Toast.makeText(this,"saved GovernorForm",Toast.LENGTH_SHORT).show()
                  }
                  else{
                      Toast.makeText(this,"Exist GovernorForm",Toast.LENGTH_SHORT).show()
                  }
                                  clearGovernor()
              }
          }

      }


   fun checkValidation()
   {
       emailFocusListener()
       NameFocusListener()
       phoneFocusListener()
       AddressFocusListener()
       cityFocusListener()
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
         code_input.editText?.setText("")
         area_input.editText?.setText("")
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
                binding.fname.helperText= validName()
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
            return "Minimum 2 Character city"
        }
        if(cityText.matches(".*[a-z].*".toRegex()))
        {
            return "Minimum 2 Character city"
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
    fun dropDown() {
        var cDetails= arrayListOf<CountyDetailsModel>()
        val db = conncetionHelper(this, null)
        val data = db.getCountyDetialsList()

        data.forEach() {
            cDetails.add(it)
        }


        val county_adapter = ArrayAdapter(this, R.layout.county_list, cDetails)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.county_type)
        autoCompleteTextView.setAdapter(county_adapter)

        autoCompleteTextView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext, " hello hello hello", Toast.LENGTH_LONG).show()
                var item = cDetails.get(position)
                var wisdom = item.county_name
                //cno_input.editText?.setText(item.fname)

                val query = "SELECT * FROM county_details  where county_name='$wisdom'"
                var dbHelp = db.readableDatabase
                val rs=dbHelp.rawQuery(query,null)

                while (rs.moveToNext()) {
                    val addC: String = rs.getString(rs.getColumnIndexOrThrow("code"))
                    val add: String = rs.getString(rs.getColumnIndexOrThrow("area"))
                    code_input.editText?.setText(addC)
                    area_input.editText?.setText(add)
                }
            }
        })
    }


    fun insertCounties(){

        val db = conncetionHelper(this, null)
        var mombasa  = db.getInserts("mombasa","001","212.5")
        var kwale = db.getInserts("kwale","002","8270.3")
        var Kilifi = db.getInserts("kilifi","003","12245.9")
        var Tana_River = db.getInserts("tana_river","004","35375.8")
        var Lamu = db.getInserts("lamu","005","6497.7")
        var Taita_Taveta = db.getInserts("taita_taveta","006","17083.9")
        var Garissa = db.getInserts("garissa","007","45720.2")
        var Wajir = db.getInserts("wajir","008","55840.6")
        var Mandera= db.getInserts("mandera","009","25797.7")
        var Marsabit = db.getInserts("marsabit","010","66923.1")
        var isiolo = db.getInserts("isiolo","011","25336.1")
        var Meru = db.getInserts("meru","012","7003.1")
        var Tharaka_Nithi = db.getInserts("tharaka_nithi","013","2609.5")
        var Embu = db.getInserts("embu","014","2555.9")
        var Kitui = db.getInserts("kitui","015","24385.1")
        var Machakos = db.getInserts("machakos","016","5952.9")
        var Makueni= db.getInserts("makueni","017","8008.9")
        var Nyandarua = db.getInserts("nyandarua","018","3107.7")
        var Nyeri = db.getInserts("nyeri","019","2361.0")
        var Kirinyaga= db.getInserts("kirinyaga","020","1205.4")
        var Muranga = db.getInserts("muranga ","021","2325.8")
        var Kiambu = db.getInserts("kiambu","022","2449.2")
        var Turkana = db.getInserts("turkana","023","71597.8")
        var Pokot = db.getInserts("west pokot","024","8418.2")
        var Samburu = db.getInserts("samburu","025","20182.5")
        var Nzoia = db.getInserts("trans nzoia ","026","2469.9")
        var Gishu = db.getInserts("uasin ishu","027","2955.3")
        var Marakwet = db.getInserts("elgeyo-marakwet","028","3049.7")
        var Nandi = db.getInserts("nandi","029","2884.5")
        var Baringo = db.getInserts("laringo","030","11075.3")
        var Laikipia = db.getInserts("laikipia","031","8696.1")
        var Nakuru = db.getInserts("nakuru","032","7509.5")
        var Narok = db.getInserts("narok ","033","17921.2")
        var kajiado = db.getInserts("kajiado","034","21292.7")
        var Kericho = db.getInserts("kericho","035","2454.5")
        var Bomet = db.getInserts("bomet","036","1997.9")
        var Kakamega = db.getInserts("kakamega","037","3033.8")
        var Vihiga = db.getInserts("vihiga ","038","531.3")
        var Bungoma = db.getInserts("bungoma","039","2206.9")
        var Busia = db.getInserts("busia","040","1628.4")
        var Siaya = db.getInserts("siaya","041","2496.1")
        var Kisumu = db.getInserts("kisumu","042","2009.5")
        var Bay = db.getInserts("homa bay ","043","3154.7")
        var Migori = db.getInserts("migori","044","2586.4")
        var Kisii = db.getInserts("kisii ","045","1317.9")
        var Nyamira = db.getInserts("nyamira","046","912.5")
        var Nairobi = db.getInserts("nairobi","047","694.9")

        if(mombasa == true)
        {
            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
        }
    }

}

