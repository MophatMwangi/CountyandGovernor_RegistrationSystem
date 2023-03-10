package com.strath.countyandgovernor_registrationsystem.views



import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import com.strath.countyandgovernor_registrationsystem.databinding.ActivityCountyFormBinding
import java.util.*
import kotlin.collections.ArrayList


class CountyForm : AppCompatActivity() {

    companion object {
        lateinit var db: conncetionHelper
    }

     private lateinit var binding : ActivityCountyFormBinding
     private lateinit var cn_input: TextInputLayout
     private lateinit var pop_input: TextInputLayout
     private lateinit var area_input: TextInputLayout
     private lateinit var not_input: TextInputLayout
     private lateinit var cno_input: TextInputLayout
     private lateinit var nos_input: TextInputLayout
     private lateinit var noh_input: TextInputLayout
     private lateinit var governor_input: TextInputLayout


     var governorlist = ArrayList<GovernorModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_county_form)
        binding = ActivityCountyFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pun()

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



         dropDown()
        //loadDropdownData()


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

            clearCounty()

            if(TextUtils.isEmpty(area) || TextUtils.isEmpty(cno) || TextUtils.isEmpty(nos) || TextUtils.isEmpty(noh))
            {
                Toast.makeText(this,"Add county number, schools and hospitals, area",Toast.LENGTH_SHORT).show()
            }else
            {
                db.addCounty(name,pop,area,not,cno,nos,noh,governors)
                Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()
            }


        }

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

   fun dropDown()
   {
       var gv = arrayListOf<GovernorModel>()
       val db = conncetionHelper(this,null)
       val data = db.getGovernorsList()
       data.forEach(){
           gv.add(it)
       }
         val simp = gv

       val gvs = GovernorModel()

       val adapter = ArrayAdapter(this, R.layout.governor_list, gv)
       val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.governor_name)
       autoCompleteTextView.setAdapter(adapter)


   }
    private fun pun()
    {
        populationFocusListener()
        notFocusListener()
        AreaFocusListener()
        countyNFocusListener()
    }

    private fun populationFocusListener()
    {
        binding.populationContainer.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.population.helperText = validPopulation()
            }
        }
    }

    private fun validPopulation(): String?
    {
        val phoneText = binding.populationContainer.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null
    }
    private fun AreaFocusListener()
    {
        binding.areaContainer.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.area.helperText = validArea()
            }
        }
    }

    private fun validArea(): String?
    {
        val areaText = binding.areaContainer.text.toString()
        if(!areaText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(areaText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null
    }
    private fun notFocusListener()
    {
        binding.areaContainer.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.area.helperText = validNot()
            }
        }
    }

    private fun validNot(): String?
    {
        val noText = binding.areaContainer.text.toString()
        if(!noText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(noText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null
    }
     private fun countyNFocusListener()
    {
        binding.areaContainer.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.area.helperText = validcountyN()
            }
        }
    }

    private fun validcountyN(): String?
    {
        val countyText = binding.areaContainer.text.toString()
        if(!countyText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(countyText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null
    }

}