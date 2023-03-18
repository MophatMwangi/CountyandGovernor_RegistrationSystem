package com.strath.countyandgovernor_registrationsystem.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.Model.CountyModel
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper

class Reports : AppCompatActivity() {

    companion object {
        lateinit var db: conncetionHelper
    }

    private lateinit var cn_out: TextView
    private lateinit var gn_out: TextView
    private lateinit var sh_out: TextView
    private lateinit var co_out: TextView
    private lateinit var pop_out: TextView
    private lateinit var area_out: TextView
    private lateinit var ourLineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        val db = conncetionHelper(this, null)

        cn_out = findViewById(R.id.hospitals_txt)
        gn_out = findViewById(R.id.governors_txt)
        sh_out = findViewById(R.id.schools_txt)
        co_out = findViewById(R.id.counties_txt)
        pop_out = findViewById(R.id.populations_txt)
        area_out = findViewById(R.id.areas_txt)
        ourLineChart = findViewById(R.id.Lchart)

        val rt = db.getHospitalList()
        val rs = db.getGovernorList()
        val rk = db.getSchoolList()
        val re = db.getCountiesList()
        val rw = db.getPopulationList()
        val rb = db.getAreaList()

        cn_out.setText(rt.toString())
        gn_out.setText(rs.toString())
        sh_out.setText(rk.toString())
        co_out.setText(re.toString())
        pop_out.setText(rw.toString())
        area_out.setText(rb.toString())

        retrieveRecordsAndPopulateCharts()

    }

    fun retrieveRecordsAndPopulateCharts() {
        //creating the instance of DatabaseHandler class
        val db = conncetionHelper(this, null)
        //calling the retreiveAnimals method of DatabaseHandler class to read the records
        val animal: List<CountyModel> = db.getCountyList(this)
        //create arrays for storing the values gotten
        val animalIDArray = Array<Int>(animal.size) { 0 }
        val animalNameArray = Array<String>(animal.size) { "natgeo" }
        val animalNumberArray = Array<String>(animal.size) { "0" }
        val animalAgeArray = Array<String>(animal.size) { "0" }
        val animalGrowthArray = Array<String>(animal.size) {"0" }

        //add the records till done
        var index = 0
        for (a in animal) {
            animalIDArray[index] = a.id
            animalNameArray[index] = a.county_name
            animalNumberArray[index] = a.area
            animalAgeArray[index] = a.number_of_towns
            animalGrowthArray[index] = a.population
            index++
        }
        //call the methods for populating the charts

        populateLineChart(animalGrowthArray)

    }

    private fun populateLineChart(values: Array<String>) {
        val ourLineChartEntries: ArrayList<Entry> = ArrayList()

        var i = 0

        for (entry in values) {
            var value = values[i].toFloat()
            ourLineChartEntries.add(Entry(i.toFloat(), value))
            i++
        }
        val lineDataSet = LineDataSet(ourLineChartEntries, "")
        lineDataSet.setColors(*ColorTemplate.PASTEL_COLORS)
        val data = LineData(lineDataSet)
        ourLineChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = ourLineChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        ourLineChart.legend.isEnabled = false

        //remove description label
        ourLineChart.description.isEnabled = false

        //add animation
        ourLineChart.animateX(1000, Easing.EaseInSine)
        ourLineChart.data = data
        //refresh
        ourLineChart.invalidate()
    }

}