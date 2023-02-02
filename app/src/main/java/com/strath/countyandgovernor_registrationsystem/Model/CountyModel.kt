package com.strath.countyandgovernor_registrationsystem.Model

import java.util.*

data class CountyModel
    (
    var id:Int = getAutoId(),
    var county_name:String = "",
    var population :String = "",
    var area:String = "",
    var number_of_towns:String= "",
    var county_number:String= "",
    var number_of_schools:String="",
    var number_of_hospitals:String="",
    var governor:String =""
            ) {

        companion object
        {
            fun getAutoId():Int
            {
                val random = Random()
                return random.nextInt(100)
            }
        }

}