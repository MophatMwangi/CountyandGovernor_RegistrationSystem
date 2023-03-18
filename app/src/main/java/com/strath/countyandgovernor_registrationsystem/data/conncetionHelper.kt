package com.strath.countyandgovernor_registrationsystem.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.strath.countyandgovernor_registrationsystem.Model.CountyDetailsModel
import com.strath.countyandgovernor_registrationsystem.Model.CountyModel
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel


class conncetionHelper (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){



    companion object{
        private val DATABASE_NAME = "CG"
        private val DATABASE_VERSION = 1

        //COUNTY DETAILS
        val TABLE_COUNTYDETAILS = "county_details"
        val CD_ID = "id"
        val CN_COL = "county_name"
        val CODE_COL = "code"
        val CAREA_COL = "area"

        val TABLE_NAME = "county_form"
        val ID_COL = "id"
        val NAME_COl = "county_name"
        val POP_COl = "population"
        val AREA_COl = "area"
        val NOT_COl = "number_of_towns"
        val COUNTY_COl = "county_number"
        val SCHOOLS_COl = "number_of_schools"
        val HOSPITALS_COl = "number_of_hospitals"
        val GOVERNORS_COl = "governors"

        //GOVERNOR FORM

        val TABLE_GOVERNOR = "governor_form"
        val GV_ID_COL = "id"
        val FNAME_COl = "first_name"
        val DOB_COl = "dob"
        val AGE_COl = "age"
        val GENDER_COl = "gender"
        val ADDRESS_COl = "address"
        val CITY_COl = "city"
        val EMAIL_COl = "email"
        val PN_COl = "phone"
        val COUNT_COl = "county"
        val COUNTCODE_COl = "county_code"
        val COUNTAREA_COl = "county_area"



        //login form
        val TABLE_LOGIN = "login_form"
        val LG_ID_COL = "id"
        val FNAMES_COl = "usernames"
        val EMAILS_COl = "email"
        val PASSWORD_COl = "passwords"



    }

    override fun onCreate(db: SQLiteDatabase) {

        // below is a sqlite query, where column names

        //county details
        val cdetails_query =("CREATE TABLE " + TABLE_COUNTYDETAILS + " ("
                + CD_ID  + " INTEGER PRIMARY KEY, " +
                CN_COL + " TEXT UNIQUE," +
                CODE_COL + " TEXT," +
                CAREA_COL+ " TEXT UNIQUE" + ")")

        // county form query
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT UNIQUE," +
                POP_COl + " TEXT," +
                AREA_COl + " TEXT," +
                NOT_COl + " TEXT," +
                COUNTY_COl + " TEXT UNIQUE," +
                SCHOOLS_COl + " TEXT," +
                HOSPITALS_COl + " TEXT," +
                GOVERNORS_COl + " TEXT UNIQUE" + ")")
            //LOGIN query
       val lquery = ("CREATE TABLE " + TABLE_LOGIN + " ("
                + LG_ID_COL + " INTEGER PRIMARY KEY, " +
                FNAMES_COl + " TEXT ," +
                EMAILS_COl + " TEXT," +
                PASSWORD_COl + " TEXT UNIQUE" + ")")
            //governor query
        val squery = ("CREATE TABLE " + TABLE_GOVERNOR + " ("
                + GV_ID_COL + " INTEGER PRIMARY KEY, " +
                FNAME_COl + " TEXT," +
                DOB_COl + " TEXT," +
                AGE_COl + " TEXT," +
                GENDER_COl + " TEXT," +
                ADDRESS_COl + " TEXT," +
                CITY_COl + " TEXT," +
                EMAIL_COl + " TEXT UNIQUE," +
                PN_COl + " TEXT," +
                COUNTAREA_COl + " TEXT," +
                COUNTCODE_COl+ " TEXT," +
                COUNT_COl + " TEXT" + ")")





        // we are calling sqlite
        // method for executing our query
        db.execSQL(cdetails_query)
        db.execSQL(query)
        db.execSQL(squery)
        db.execSQL(lquery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTYDETAILS)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOVERNOR)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN)
        onCreate(db)
    }
    //this is a trial
    fun getInserts(s: String, s1: String,s2: String): Boolean
    {
        val db = this.writableDatabase
        val contentValues = ContentValues();
        contentValues.put("county_name", s)
        contentValues.put("code", s1)
        contentValues.put("area", s2)

        val result = db.insert(TABLE_COUNTYDETAILS, null, contentValues);

        if(result == -1.toLong())
        {
            return false
        }
           return true
    }

      // This method is for adding data in our database
      @SuppressLint("SuspiciousIndentation")
      fun addCounty(name : String, pop : String, area :String, no_towns :String, county_no :String, no_schools :String, no_hospitals :String, governors :String) : Boolean{
        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, name)
        values.put(POP_COl, pop)
        values.put(AREA_COl, area)
        values.put(NOT_COl, no_towns)
        values.put(COUNTY_COl, county_no)
        values.put(SCHOOLS_COl, no_schools)
        values.put(HOSPITALS_COl, no_hospitals)
        values.put(GOVERNORS_COl, governors)
        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        val result = db.insert(TABLE_NAME, null, values)
          if (result==-1.toLong()){
              return false
          }
          return true
    }

    // This method is for adding data in our database
    fun addGovernor( fname : String, dob: String,gender :String, age :String,address : String,city :String, email :String, phone_number :String, county :String, area: String,
        code: String) : Boolean {

        val values = ContentValues()

        values.put(FNAME_COl, fname)
        values.put(DOB_COl, dob)
        values.put(AGE_COl, age)
        values.put(GENDER_COl, gender)
        values.put(ADDRESS_COl, address)
        values.put(CITY_COl, city)
        values.put(EMAIL_COl, email)
        values.put(PN_COl, phone_number)
        values.put(COUNT_COl, county)
        values.put(COUNTAREA_COl, area)
        values.put(COUNTCODE_COl, code)


        val db = this.writableDatabase

        // all values are inserted into database
       val result= db.insert(TABLE_GOVERNOR, null, values)
        if (result==-1.toLong()){
            return false
        }
        return true
    }
    // This method is for adding data in our database
    fun addLogin( name : String, email : String, password :String) : Boolean {
        val values = ContentValues()

        values.put( FNAMES_COl, name)
        values.put(EMAILS_COl, email)
        values.put(PASSWORD_COl, password)

        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        val result = db.insert(TABLE_LOGIN, null, values)
        if (result==-1.toLong()){
            return false
        }
        return true
    }

    // below method is to get
    // all data from our database
    fun getCountyList(ctx:Context):ArrayList<CountyModel> {

        val countyList = ArrayList<CountyModel> ()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor = db.rawQuery(selectQuery,null)

        var id: Int
        var name : String
        var pop : String
        var area :String
        var no_towns :String
        var county_no :String
        var no_schools :String
        var no_hospitals :String
        var governor :String

         if(cursor.count == 0)
             Toast.makeText(ctx,"No Records Found",Toast.LENGTH_SHORT).show()
             else
         {

             while(cursor.moveToNext()){

                 id= cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                 name = cursor.getString(cursor.getColumnIndexOrThrow("county_name"))
                 pop = cursor.getString(cursor.getColumnIndexOrThrow("population"))
                 area= cursor.getString(cursor.getColumnIndexOrThrow("area"))
                 no_towns= cursor.getString(cursor.getColumnIndexOrThrow("number_of_towns"))
                 county_no= cursor.getString(cursor.getColumnIndexOrThrow("county_number"))
                 no_schools= cursor.getString(cursor.getColumnIndexOrThrow("number_of_schools"))
                 no_hospitals = cursor.getString(cursor.getColumnIndexOrThrow("number_of_hospitals"))
                 governor = cursor.getString(cursor.getColumnIndexOrThrow("governors"))

                 val county = CountyModel(id=id, county_name = name, population = pop,area=area, number_of_towns = no_towns, county_number = county_no, number_of_schools = no_schools, number_of_hospitals =no_hospitals ,governor=governor )
                 countyList.add(county)

             }
             Toast.makeText(ctx,"${cursor.count.toString()}Records Found",Toast.LENGTH_SHORT).show()

         }
           cursor.close()
           db.close()
           return countyList
    }

    //used to collect the name of the county, area, and code
    fun getCountyDetialsList(): ArrayList<CountyDetailsModel> {

        val countyDetailsList = ArrayList<CountyDetailsModel> ()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_COUNTYDETAILS"
        val rs = db.rawQuery(selectQuery,null)

        if(rs.moveToFirst()){
            do{

                val cDetials = CountyDetailsModel()
                cDetials.id = rs.getInt(rs.getColumnIndexOrThrow(CD_ID))
                cDetials.county_name = rs.getString(rs.getColumnIndexOrThrow(CN_COL))


                countyDetailsList .add(cDetials)

            }while(rs.moveToNext())
        }


        rs.close()
        db.close()
        return  countyDetailsList
    }

    //used as a drop to select governor's name
    fun getGovernorsList(): ArrayList<GovernorModel> {

        val governorList = ArrayList<GovernorModel> ()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_GOVERNOR"
        val rs = db.rawQuery(selectQuery,null)

        if(rs.moveToFirst()){
            do{

                val governorL = GovernorModel()
                governorL.id = rs.getInt(rs.getColumnIndexOrThrow(GV_ID_COL))
                governorL.fname = rs.getString(rs.getColumnIndexOrThrow(FNAME_COl))
                governorList.add(governorL)

            }while(rs.moveToNext())
        }


        rs.close()
        db.close()
        return governorList
    }


//Reports
    fun getHospitalList() : Int {


        var sum : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT sum(number_of_hospitals) as number_of_hospitals from county_form"
        val rs = db.rawQuery(selectQuery,null)
      if( rs.moveToFirst()){
        sum = rs.getInt(rs.getColumnIndexOrThrow("number_of_hospitals"))
        }
        return sum
    }


    fun getCountiesList() : Int {
        var sum : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT count(county_name) as county_name from county_form"
        val rs = db.rawQuery(selectQuery,null)
        if( rs.moveToFirst()){
            sum = rs.getInt(rs.getColumnIndexOrThrow("county_name"))
        }
        return sum
    }

    fun getSchoolList() : Int {


        var sum : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT sum(number_of_schools) as number_of_schools from county_form"
        val rs = db.rawQuery(selectQuery,null)
        if( rs.moveToFirst()){
            sum = rs.getInt(rs.getColumnIndexOrThrow("number_of_schools"))
        }
        return sum
    }


    fun getGovernorList() : Int {


        var sum : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT count(governors) as governors from county_form"
        val rs = db.rawQuery(selectQuery,null)
        if( rs.moveToFirst()){
            sum = rs.getInt(rs.getColumnIndexOrThrow("governors"))
        }
        return sum
    }

    fun getPopulationList() : Int {


        var sum : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT   sum(population)  as population from county_form"
        val rs = db.rawQuery(selectQuery,null)
        if( rs.moveToFirst()){
            sum = rs.getInt(rs.getColumnIndexOrThrow("population"))
        }
        return sum
    }

    fun getAreaList() : Int {


        var sum : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT sum(area) as area from county_form"
        val rs = db.rawQuery(selectQuery,null)
        if( rs.moveToFirst()){
            sum = rs.getInt(rs.getColumnIndexOrThrow("area"))
        }
        return sum
    }
//report

     //delete county by id
    fun deleteCounty(countyID : Int):Boolean
    {
        val qry = "Delete From $TABLE_NAME where $ID_COL = $countyID"
        val db : SQLiteDatabase = this.writableDatabase
        db.execSQL(qry)
        val result = false
        try {
            //val cursor:Int = db.delete(TABLE_NAME,"$ID_COL=?", arrayOf(countyID.toString()))
             //val cursor:Unit  = db.execSQL(qry)
            Log.e(ContentValues.TAG," Deleted successfully")
        }catch (e: Exception)
        {
          Log.e(ContentValues.TAG,"Error Deleting")
        }
        db.close()
        return result
    }


    fun getGovernorList(ctx:Context):ArrayList<GovernorModel> {

        val governorList = ArrayList<GovernorModel> ()
        val selectQuery = "SELECT * FROM $TABLE_GOVERNOR"
        val db = this.readableDatabase

        val cursor = db.rawQuery(selectQuery,null)

        var id: Int
        var fname : String
        var dob: String
        var age: String
        var gender :String
        var address : String
        var city :String
        var email :String
        var phone_number :String
        var county :String

        if(cursor.count == 0)
            Toast.makeText(ctx,"No Records Found",Toast.LENGTH_SHORT).show()
        else
        {

            while(cursor.moveToNext()){

                id= cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                fname = cursor.getString(cursor.getColumnIndexOrThrow("first_name"))
                dob = cursor.getString(cursor.getColumnIndexOrThrow("dob"))
                age = cursor.getString(cursor.getColumnIndexOrThrow("age"))
                gender= cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                address= cursor.getString(cursor.getColumnIndexOrThrow("address"))
                city= cursor.getString(cursor.getColumnIndexOrThrow("city"))
                email= cursor.getString(cursor.getColumnIndexOrThrow("email"))
                phone_number = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                county = cursor.getString(cursor.getColumnIndexOrThrow("county"))

                val governor = GovernorModel(
                    id=id,
                    fname = fname,
                    dob = dob,
                    age = age,
                    gender=gender,
                    address= address,
                    city = city,
                    email = email,
                    phone_number =phone_number,
                    county=county
                )
                governorList.add(governor)


            }
            Toast.makeText(ctx,"${cursor.count.toString()}Records Found",Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return governorList
    }


    fun deleteGovernor(governorID : Int):Boolean
    {
       // val qry = "Delete From $TABLE_NAME where $ID_COL = $countyID"
        val qry = "Delete From $TABLE_GOVERNOR where $GV_ID_COL = $governorID"
        val db : SQLiteDatabase = this.writableDatabase
        db.execSQL(qry)
        val result = false
        db.close()
        return result
    }


    fun updateCounty(id:String,pop : String,no_towns :String,no_schools :String,no_hospitals :String,governor :String)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(POP_COl,pop)
        contentValues.put(NOT_COl,no_towns)
        contentValues.put(SCHOOLS_COl,no_schools)
        contentValues.put(HOSPITALS_COl,no_hospitals)
        contentValues.put(GOVERNORS_COl,governor)

           db.update(TABLE_NAME,contentValues,"$ID_COL=?", arrayOf(id))
           db.close()

    }

    fun updateGovernor(id:String,fname : String , dob: String ,gender :String,address : String,email :String )
    {
        val db = this.writableDatabase
        val cv= ContentValues()

        cv.put(FNAME_COl, fname)
        cv.put(DOB_COl, dob)
        cv.put(GENDER_COl, gender)
        cv.put(ADDRESS_COl, address)
        cv.put(EMAIL_COl, email)

        db.update(TABLE_GOVERNOR,cv,"$GV_ID_COL=?", arrayOf(id))
        db.close()

    }




    fun checkUser(username: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(LG_ID_COL)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$FNAMES_COl= ? AND $PASSWORD_COl = ?"
        // selection arguments
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(
            TABLE_LOGIN, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }
}




