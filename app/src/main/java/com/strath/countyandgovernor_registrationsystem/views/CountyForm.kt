package com.strath.countyandgovernor_registrationsystem.views



import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import com.strath.countyandgovernor_registrationsystem.databinding.ActivityCountyFormBinding
import java.util.*


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

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountyFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pun()

        val display = supportActionBar
        display?.title = "CountyForm"
        display?.setDefaultDisplayHomeAsUpEnabled(true)



         //input textfield
         cn_input = findViewById<TextInputLayout>(R.id.countyname)
         pop_input= findViewById<TextInputLayout>(R.id.population)
         area_input=findViewById<TextInputLayout>(R.id.area)
         not_input= findViewById<TextInputLayout>(R.id.towns)
         cno_input= findViewById<TextInputLayout>(R.id.countyN)
         nos_input= findViewById<TextInputLayout>(R.id.school)
         noh_input= findViewById<TextInputLayout>(R.id.hospitals)
         governor_input= findViewById<TextInputLayout>(R.id.governors)



         dropDown()
        //loadDropdownData()


        //button
        val addbutton = findViewById<AppCompatButton>(R.id.add_county)
        addbutton.setOnClickListener{
            if (TextUtils.isEmpty(binding.countynametype.text.toString())) {
                binding.countyname.isErrorEnabled = false
                binding.countyname.error = "enter county name"
            } else if (TextUtils.isEmpty(binding.countyContainer.text.toString())) {
                binding.countyN.isErrorEnabled = false
                binding.countyN.error = "enter county number"

            } else if (TextUtils.isEmpty(binding.populationContainer.text.toString())) {
                binding.population.isErrorEnabled = false
                binding.population.error = "enter population"
            } else if (TextUtils.isEmpty(binding.areaContainer.text.toString())) {
                binding.area.isErrorEnabled = false
                binding.area.error = "enter number of cities"
            } else if (TextUtils.isEmpty(binding.townContainer.text.toString())) {
                binding.towns.isErrorEnabled = false
                binding.towns.error = "enter number of towns"
            } else if (TextUtils.isEmpty(binding.schoolContainer.text.toString())) {
                binding.school.isErrorEnabled = false
                binding.school.error = "enter number of schools"}
            else if (TextUtils.isEmpty(binding.hospitalContainer.text.toString())) {
                binding.hospitals.isErrorEnabled = false
                binding.hospitals.error = "enter number of hospitals"}else {
                // Toast.makeText(this,"Hello morphy", LENGTH_SHORT).show()
                val db = conncetionHelper(this, null)
                val name = cn_input.editText?.text.toString()
                val pop = pop_input.editText?.text.toString()
                val area = area_input.editText?.text.toString()
                val not = not_input.editText?.text.toString()
                val cno = cno_input.editText?.text.toString()
                val nos = nos_input.editText?.text.toString()
                val noh = noh_input.editText?.text.toString()
                val governors = governor_input.editText?.text.toString()

                clearCounty()

                if (TextUtils.isEmpty(area) || TextUtils.isEmpty(cno) || TextUtils.isEmpty(nos) || TextUtils.isEmpty(
                        noh
                    )
                ) {
                    Toast.makeText(
                        this,
                        "Add county number, schools and hospitals, area",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val saveData = db.addCounty(name, pop, area, not, cno, nos, noh, governors)

                    if (saveData == true) {
                        Toast.makeText(this, "saved ", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "exists ", Toast.LENGTH_SHORT).show()
                    }

                }

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


       val adapter = ArrayAdapter(this, R.layout.governor_list, gv)
       val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.governor_name)
       autoCompleteTextView.setAdapter(adapter)

       var gov = GovernorModel()
       gov.id

       autoCompleteTextView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
           override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               Toast.makeText(applicationContext, " hello hello hello", Toast.LENGTH_LONG).show()
               var item = gv.get(position)
               var wisdom = item.fname
               //cno_input.editText?.setText(item.fname)

               val query = "SELECT * FROM  governor_form where first_name='$wisdom'"
               var dbHelp = db.readableDatabase
               val rs=dbHelp.rawQuery(query,null)

               while (rs.moveToNext()) {
                   val addC: String = rs.getString(rs.getColumnIndexOrThrow("county"))
                   val addb: String = rs.getString(rs.getColumnIndexOrThrow("county_area"))
                   val add: String = rs.getString(rs.getColumnIndexOrThrow("county_code"))
                   cn_input.editText?.setText(addC)
                   area_input.editText?.setText(addb)
                   cno_input.editText?.setText(add)
                   //System.out.println(add);
               }
           }
       })

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
            return "Must be all Digits"
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
            return "Must be all Digits"
        }
        return null
    }

}