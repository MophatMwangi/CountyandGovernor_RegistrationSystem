package com.strath.countyandgovernor_registrationsystem.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.Lands



class EdFarmAdapter(nctx: Context, val myFarmList: List<Lands>):  RecyclerView.Adapter<EdFarmAdapter.EdFarmViewHolder>()  {

    val nctx = nctx

    class EdFarmViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val land_name = view.findViewById<TextView>(R.id.land_txt)
        val location_name = view.findViewById<TextView>(R.id.location_txt)
        val soil_name = view.findViewById<TextView>(R.id.soil_txt)

        fun bindData(myFarms: Lands){
            land_name.text = myFarms.name
            location_name.text = myFarms.location
            soil_name.text = myFarms.soil_type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EdFarmViewHolder {
        return EdFarmViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_edfarm,parent,false))
    }

    override fun getItemCount(): Int {
        return myFarmList.size
    }

    override fun onBindViewHolder(holder: EdFarmViewHolder, position: Int) {
     holder.bindData(myFarmList[position])
    }


}