package com.strath.countyandgovernor_registrationsystem.Model

import java.util.*

class Governors (
    var id:Int = CountyModel.getAutoId(),
    var governor: String="",
    var countyCode: String=""
        ){

    companion object
    {
        fun getAutoId():Int
        {
            val random = Random()
            return random.nextInt(100)
        }
    }

    override fun toString(): String {
        //return "GovernorModel(fname='$fname')"
        return "$governor"+"$countyCode"
    }

}