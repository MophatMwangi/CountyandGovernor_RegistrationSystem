package com.strath.countyandgovernor_registrationsystem.Model

import java.util.*

data class GovernorModel
    (
    var id:Int = CountyModel.getAutoId(),
    var fname : String = "",
    var dob: String = "",
    var gender :String = "",
    var address : String = "",
    var city :String = "",
    var email :String = "",
    var phone_number :String = "",
    var county :String = ""
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

}