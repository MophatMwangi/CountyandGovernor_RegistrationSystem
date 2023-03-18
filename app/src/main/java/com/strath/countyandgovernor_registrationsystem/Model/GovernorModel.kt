package com.strath.countyandgovernor_registrationsystem.Model

import java.util.*

class GovernorModel(
    var id: Int = CountyModel.getAutoId(),
    var fname: String = "",
    var dob: String = "",
    var age: String = "",
    var gender: String = "",
    var address: String = "",
    var city: String = "",
    var email: String = "",
    var phone_number: String = "",
    var county: String = "",
    var area: String = "",
    var code : String =""
)
{


    companion object
    {
        fun getAutoId():Int
        {
            val random = Random()
            return random.nextInt(100)
        }
    }

    override fun toString(): String {

         return "$fname"
    }



}

