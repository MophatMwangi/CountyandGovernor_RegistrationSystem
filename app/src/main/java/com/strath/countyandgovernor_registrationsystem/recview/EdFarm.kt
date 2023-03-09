package com.strath.countyandgovernor_registrationsystem.recview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.strath.countyandgovernor_registrationsystem.Adapters.EdFarmAdapter
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.ApiInterface
import com.strath.countyandgovernor_registrationsystem.data.FarmData
import com.strath.countyandgovernor_registrationsystem.data.Lands
import com.strath.countyandgovernor_registrationsystem.data.RetrofitInstance
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

//@AndroidEntryPoint
class EdFarm : AppCompatActivity() {

    //val BASE_URL = "https://edfafarmer.appspot.com/"

   // @Inject
   // lateinit var apiService :ApiInterface

    lateinit var myAdapter: EdFarmAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ed_farm)

        // rv.hasFixedSize(true)
        rv = findViewById(R.id.recyclerViewEdFarm)
        linearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager


        getRetro()
    }
    fun getRetro() {
        val retService = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val responseLiveData: LiveData<Response<FarmData>> = liveData {

            val response: Response<FarmData> = retService.getData()
            emit(response)


        }
        responseLiveData.observe(this, Observer {

            //val FarmList: MutableListIterator<Lands> = it.body()!! as MutableListIterator<Lands>

            val farming = it.body()!!
            myAdapter = EdFarmAdapter(this,farming.lands_list)
            myAdapter.notifyDataSetChanged()
            rv.adapter=myAdapter

            //used to display the data that belongs to the last api array in the recycler view
            /*for(myData in FarmList)
            {

                Log.i("MYTAG", myData.location)
                myAdapter = EdFarmAdapter(this,listOf(myData) )
                //myAdapter.notifyDataSetChanged()
                rv.adapter=myAdapter

            }*/


        })
    }

    fun getRet()
    {
        val retService = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)

    }


}