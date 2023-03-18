package com.strath.countyandgovernor_registrationsystem.Model

import java.util.*

class CountyDetailsModel (
    var id:Int = CountyModel.getAutoId(),
    var county_name : String = "",
    var code: String = "",
    var area :String = "",){

    companion object
    {
        fun getAutoId():Int
        {
            val random = Random()
            return random.nextInt(100)
        }
    }

    override fun toString(): String {

        return "$county_name"
    }
}