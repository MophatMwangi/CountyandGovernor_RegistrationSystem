package com.strath.countyandgovernor_registrationsystem.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.strath.countyandgovernor_registrationsystem.R
import com.strath.countyandgovernor_registrationsystem.data.conncetionHelper

class Sign : AppCompatActivity() {

    private lateinit var fn_input: TextInputLayout
    private  lateinit var email_input : TextInputLayout
    private lateinit var pass_input: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)


        fn_input = findViewById<TextInputLayout>(R.id.username)
        email_input= findViewById<TextInputLayout>(R.id.email)
        pass_input =findViewById<TextInputLayout>(R.id.passwordsg)


        //button
        val addbutton = findViewById<AppCompatButton>(R.id.sign_up_btn)

        addbutton.setOnClickListener{

            // Toast.makeText(this,"Hello morphy", LENGTH_SHORT).show()
            val db = conncetionHelper(this, null)

            val fname = fn_input.editText?.text.toString()
            val email =email_input.editText?.text.toString()
            val password = pass_input.editText?.text.toString()




            clearSign()
            if(TextUtils.isEmpty(fname) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            {
                Toast.makeText(this,"Add username, email and password",Toast.LENGTH_SHORT).show()
            }else
            {

                val saveData = db.addLogin(fname,email,password)
                if (saveData == true){
                    Toast.makeText(this,"saved ",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"username exist ",Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this , Login::class.java)
                startActivity(intent)
            }

        }
    }

    fun clearSign()
    {
        fn_input.editText?.setText("")
        email_input.editText?.setText("")
        pass_input.editText?.setText("")

    }
}