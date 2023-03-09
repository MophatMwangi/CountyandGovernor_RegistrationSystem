package com.strath.countyandgovernor_registrationsystem.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.dashboard.Dashboard
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper

class Login : AppCompatActivity() {

    private lateinit var fn_input: TextInputLayout
    private lateinit var pass_input: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fn_input = findViewById<TextInputLayout>(R.id.username)
        pass_input =findViewById<TextInputLayout>(R.id.password)

        val addbutton = findViewById<AppCompatButton>(R.id.login)

        var db = conncetionHelper(this, null)
        var dbHelp = db.readableDatabase
        addbutton.setOnClickListener{
        var username=fn_input.editText?.text.toString();
        var password=pass_input.editText?.text.toString()
        val query="SELECT * FROM login_form WHERE usernames='"+username+"' AND passwords='"+password+"'"
        val rs=dbHelp.rawQuery(query,null)
        if(rs.moveToFirst()){
            val name=rs.getString(rs.getColumnIndexOrThrow("usernames"))
            val pass = rs.getString(rs.getColumnIndexOrThrow("passwords"))
            rs.close()
            val intent = Intent(this , Dashboard::class.java)
            startActivity(intent)

        }
        else{
            var ad = AlertDialog.Builder(this)
            ad.setTitle("Message")
            ad.setMessage("Username or password is incorrect!")
            ad.setPositiveButton("Ok", null)
            ad.show()
        }
    }
 }
}