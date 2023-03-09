package com.strath.countyandgovernor_registrationsystem.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent.getIntent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.MainActivity
import com.strath.countyandgovernor_registrationsystem.Model.CountyModel
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper
import com.strath.countyandgovernor_registrationsystem.recview.countyRecView
import java.util.zip.Inflater


class CountyAdapter(nctx : Context, val county: ArrayList<CountyModel> ):  RecyclerView.Adapter<CountyAdapter.CountyViewHolder>()   {



     val nctx = nctx



    inner class CountyViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var cn = view.findViewById<TextView>(R.id.countyname_txt)
        var pop = view.findViewById<TextView>(R.id.pop_txt)
        var area = view.findViewById<TextView>(R.id.area_txt)
        var no_towns = view.findViewById<TextView>(R.id.not_txt)
        var c_no = view.findViewById<TextView>(R.id.county_no_txt)
        var no_schools = view.findViewById<TextView>(R.id.nos_txt)
        var no_hospitals = view.findViewById<TextView>(R.id.noh_txt)
        var gv = view.findViewById<TextView>(R.id.cgovernor_txt)
        var btnDelete = view.findViewById<Button>(R.id.delete_btn)
         var btnEdit = view.findViewById<Button>(R.id.edit_btn)


        fun bindView(county: CountyModel)
        {


            cn.text = county.county_name
           pop.text = county.population
           area.text = county.area
           no_towns.text = county.number_of_towns
           c_no.text = county.county_number
           no_schools.text = county.number_of_schools
           no_hospitals.text = county.number_of_hospitals
           gv.text = county.governor

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountyViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.row_county,parent,false)


    )

    override fun getItemCount(): Int {
       return county.size
    }

    override fun onBindViewHolder(holder: CountyViewHolder, position: Int) {
        val countys = county[position]
        holder.bindView(countys)



        holder.btnDelete.setOnClickListener {
            val countyname:String = countys.county_name
            var alertDialog : AlertDialog? = AlertDialog.Builder(nctx)
                .setTitle("Warning")
                .setMessage("Are You sure to Delete: $countyname ?" )
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if (countyRecView.db.deleteCounty(countys.id))
                    {
                            county.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position,county.size)
                        Toast.makeText(nctx,"County $countyname",Toast.LENGTH_SHORT).show()
                    }else
                    {
                        Toast.makeText(nctx,"Error Deleting",Toast.LENGTH_SHORT).show()
                    }
                }).setNegativeButton("No",DialogInterface.OnClickListener{dialog, which ->  })
                .setIcon(R.drawable.baseline_delete_24)
                .show()

        }




        holder.btnEdit.setOnClickListener {
            val inflater = LayoutInflater.from(nctx) as LayoutInflater
             val  view = inflater.inflate(R.layout.update_county,null) as View



            val txtPop : TextInputLayout = view.findViewById(R.id.edit_population)
            val txtTowns : TextInputLayout  = view.findViewById(R.id.edit_no_towns)
            val txtSchool : TextInputLayout  = view.findViewById(R.id.edit_no_school)
            val txtHospital: TextInputLayout  = view.findViewById(R.id.edit_no_hospitals)
            val txtGovernor: TextInputLayout  = view.findViewById(R.id.edit_governors_names)

            val pop=  txtPop.editText?.setText(countys.population)
            val town = txtTowns.editText?.setText(countys.number_of_towns)
            val school=txtSchool.editText?.setText(countys.number_of_schools)
            val hospital =txtHospital.editText?.setText(countys.number_of_hospitals)
            val gov= txtGovernor.editText?.setText(countys.governor)

            var builder = AlertDialog.Builder(nctx)
                .setTitle("Update CountyInfo.")
                .setView(view)
                .setPositiveButton("Update",DialogInterface.OnClickListener { dialog, which ->
                   val isUpdate = countyRecView.db.updateCounty(
                       countys.id.toString(),
                       txtPop.editText?.text.toString(),
                       txtTowns.editText?.text.toString(),
                       txtSchool.editText?.text.toString(),
                       txtHospital.editText?.text.toString(),
                       txtGovernor.editText?.text.toString()
                   )

                }).setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which ->  })
                val alert = builder.create()
                alert.show()
        }




    }
}