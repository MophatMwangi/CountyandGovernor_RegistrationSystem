package com.strath.countyandgovernor_registrationsystem.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.Model.CountyModel
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.recview.countyRecView
import com.strath.countyandgovernor_registrationsystem.recview.governorRecView


class GovernorAdapter(nctx : Context, val governors: ArrayList<GovernorModel> ):RecyclerView.Adapter<GovernorAdapter.ViewHolder>() {

    val nctx = nctx

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GovernorAdapter.ViewHolder {
     val v = LayoutInflater.from(parent.context).inflate(R.layout.row_governor,parent,false)
     return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val governor = governors[position]
        holder.bindView(governor)

        holder.btnEdit.setOnClickListener {
            val inflater = LayoutInflater.from(nctx) as LayoutInflater
            val  view = inflater.inflate(R.layout.update_governor,null) as View



            val txt_fname: TextInputLayout = view.findViewById(R.id.update_f_name)
            val txt_dob : TextInputLayout = view.findViewById(R.id.update_dob)
            val txt_gender : TextInputLayout = view.findViewById(R.id.update_gender)
            val txt_address: TextInputLayout = view.findViewById(R.id.update_address)
            val txt_email: TextInputLayout = view.findViewById(R.id.update_email)

            val fname=  txt_fname.editText?.setText(governor.fname)
            val dob = txt_dob.editText?.setText(governor.dob)
            val gender=txt_gender.editText?.setText(governor.gender)
            val address =txt_address.editText?.setText(governor.address)
            val email= txt_email.editText?.setText(governor.email)

            var builder = AlertDialog.Builder(nctx)
                .setTitle("Update CountyInfo.")
                .setView(view)
                .setPositiveButton("Update",DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate = governorRecView.db.updateGovernor(
                        governor.id.toString(),
                        txt_fname.editText?.text.toString(),
                        txt_dob.editText?.text.toString(),
                        txt_gender.editText?.text.toString(),
                        txt_address.editText?.text.toString(),
                        txt_email.editText?.text.toString()
                    )

                }).setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which ->  })
            val alert = builder.create()
            alert.show()
        }


        holder.btnDelete.setOnClickListener {
            val governor_name:String = governor.fname
            var alertDialog : AlertDialog? = AlertDialog.Builder(nctx)
                .setTitle("Warning")
                .setMessage("Are You sure to Delete: $governor_name ?" )
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if (governorRecView.db.deleteGovernor(governor.id))
                    {
                        governors.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position,governors.size)
                        Toast.makeText(nctx,"County $governor_name", Toast.LENGTH_SHORT).show()
                    }else
                    {
                        Toast.makeText(nctx,"Error Deleting", Toast.LENGTH_SHORT).show()
                    }
                }).setNegativeButton("No", DialogInterface.OnClickListener{ dialog, which ->  })
                .setIcon(R.drawable.baseline_delete_24)
                .show()

        }
    }



    override fun getItemCount(): Int {
        return governors.size
    }


    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    {
       var txt_fname = itemView.findViewById<TextView>(R.id.f_name_txt)
       var txt_dob = itemView.findViewById<TextView>(R.id.dob_txt)
       var txt_gender = itemView.findViewById<TextView>(R.id.gender_txt)
       var txt_address = itemView.findViewById<TextView>(R.id.address_txt)
       var txt_city = itemView.findViewById<TextView>(R.id.city_txt)
       var txt_email = itemView.findViewById<TextView>(R.id.email_txt)
       var txt_pn = itemView.findViewById<TextView>(R.id.pn_txt)
       var txt_county = itemView.findViewById<TextView>(R.id.county_txt)
        var btnDelete = itemView.findViewById<ImageButton>(R.id.delete_btn_gv)
        var btnEdit = itemView.findViewById<ImageButton>(R.id.edit_btn_gv)

        fun bindView(governor: GovernorModel)
        {
            txt_fname.text  = governor.fname
            txt_dob.text  = governor.dob
            txt_gender.text = governor.gender
            txt_address.text  = governor.address
            txt_city.text  = governor.city
            txt_email.text = governor.email
            txt_pn.text = governor.phone_number
            txt_county.text = governor.county
        }

    }

}