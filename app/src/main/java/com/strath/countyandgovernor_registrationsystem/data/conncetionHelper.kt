package com.strath.countyandgovernor_registrationsystem.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.strath.countyandgovernor_registrationsystem.Model.CountyModel
import com.strath.countyandgovernor_registrationsystem.Model.GovernorModel


class conncetionHelper (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){



    companion object{
        private val DATABASE_NAME = "CG"
        private val DATABASE_VERSION = 1

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
        val GENDER_COl = "gender"
        val ADDRESS_COl = "address"
        val CITY_COl = "city"
        val EMAIL_COl = "email"
        val PN_COl = "phone"
        val COUNT_COl = "county"

        //login form
        val TABLE_LOGIN = "login_form"
        val LG_ID_COL = "id"
        val FNAMES_COl = "usernames"
        val EMAILS_COl = "email"
        val PASSWORD_COl = "passwords"



    }

    override fun onCreate(db: SQLiteDatabase) {

        // below is a sqlite query, where column names
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
            //governor query
       val lquery = ("CREATE TABLE " + TABLE_LOGIN + " ("
                + LG_ID_COL + " INTEGER PRIMARY KEY, " +
                FNAMES_COl + " TEXT ," +
                EMAILS_COl + " TEXT," +
                PASSWORD_COl + " TEXT UNIQUE" + ")")
            //login query
        val squery = ("CREATE TABLE " + TABLE_GOVERNOR + " ("
                + GV_ID_COL + " INTEGER PRIMARY KEY, " +
                FNAME_COl + " TEXT UNIQUE," +
                DOB_COl + " TEXT," +
                GENDER_COl + " TEXT," +
                ADDRESS_COl + " TEXT," +
                CITY_COl + " TEXT UNIQUE," +
                EMAIL_COl + " TEXT," +
                PN_COl + " TEXT UNIQUE," +
                COUNT_COl + " TEXT UNIQUE" + ")")


        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
        db.execSQL(squery)
        db.execSQL(lquery)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOVERNOR)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addCounty(
        name : String,
        pop : String,
        area :String,
        no_towns :String,
        county_no :String,
        no_schools :String,
       no_hospitals :String,
        governors :String,
        ){
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
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // This method is for adding data in our database
    fun addGovernor(
        fname : String,
        dob: String,
        gender :String,
        address : String,
        city :String,
        email :String,
        phone_number :String,
        county :String

    ){

        val values = ContentValues()

        values.put(FNAME_COl, fname)
        values.put(DOB_COl, dob)
        values.put(GENDER_COl, gender)
        values.put(ADDRESS_COl, address)
        values.put(CITY_COl, city)
        values.put(EMAIL_COl, email)
        values.put(PN_COl, phone_number)
        values.put(COUNT_COl, county)

        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_GOVERNOR, null, values)

        db.close()
    }
    // This method is for adding data in our database
    fun addLogin(
        name : String,
        email : String,
        password :String

    ){
        val values = ContentValues()

        values.put( FNAMES_COl, name)
        values.put(EMAILS_COl, email)
        values.put(PASSWORD_COl, password)

        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_LOGIN, null, values)

        db.close()
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

    fun getGovernorsList(): ArrayList<GovernorModel>/* MutableList<GovernorModel>*/ {

        //var governorList :MutableList<GovernorModel> =ArrayList()
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

    fun getCList() : Int {


        var sum : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT population ,sum(population) as totalPop from county_form"
        val rs = db.rawQuery(selectQuery,null)

        if(rs.moveToFirst())

          sum = rs.getInt(rs.getColumnIndexOrThrow("totalPop "))

        return sum
    }


    fun deleteCounty(countyID : Int):Boolean
    {
        val qry = "Delete From $TABLE_NAME where $ID_COL = $countyID"
        val db : SQLiteDatabase = this.writableDatabase
        val result = false
        try {
            //val cursor:Int = db.delete(TABLE_NAME,"$ID_COL=?", arrayOf(countyID.toString()))
             //val cursor:Unit  = db.execSQL(qry)
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
                gender= cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                address= cursor.getString(cursor.getColumnIndexOrThrow("address"))
                city= cursor.getString(cursor.getColumnIndexOrThrow("city"))
                email= cursor.getString(cursor.getColumnIndexOrThrow("email"))
                phone_number = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                county = cursor.getString(cursor.getColumnIndexOrThrow("county"))

                val governor = GovernorModel(id=id, fname = fname, dob = dob, gender=gender, address= address, city = city, email = email, phone_number =phone_number ,county=county )
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
        val qry = "Delete From $TABLE_GOVERNOR where $GV_ID_COL = $governorID"
        val db : SQLiteDatabase = this.writableDatabase
        val result = false
        try {
            //val cursor:Int = db.delete(TABLE_NAME,"$ID_COL=?", arrayOf(countyID.toString()))
            //val cursor:Unit  = db.execSQL(qry)
        }catch (e: Exception)
        {
            Log.e(ContentValues.TAG,"Error Deleting")
        }
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




