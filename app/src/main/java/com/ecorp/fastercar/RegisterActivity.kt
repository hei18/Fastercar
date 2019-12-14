package com.ecorp.fastercar

import android.content.Intent
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.ecorp.fastercar.ChildAtribute.UserAccount
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    lateinit var Edit_ktp:EditText
    lateinit var Edit_name:EditText
    lateinit var Edit_phone:EditText
    lateinit var Edit_email:EditText
    lateinit var Edit_password:EditText

    lateinit var userList: MutableList<UserAccount>

//===================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        Edit_ktp = findViewById(R.id.Edit_ktp)
        Edit_name = findViewById(R.id.Edit_name)
        Edit_phone = findViewById(R.id.Edit_phone)
        Edit_email = findViewById(R.id.Edit_email)
        Edit_password = findViewById(R.id.Edit_password)

        userList = mutableListOf()

        btn_register.setOnClickListener {
            doRegister()
        }

        toLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

        }
    }

    override fun onBackPressed() {

    }




    private fun doRegister(){
        val ktp = Edit_ktp.text.toString().trim()
        val nama = Edit_name.text.toString().trim()
        val hp = Edit_phone.text.toString().trim()
        val email = Edit_email.text.toString().trim()
        val password = Edit_password.text.toString().trim()

        if (ktp.isEmpty()){
            Edit_ktp.error = "Please Fill Civil Id !"
            return
        }
        if (nama.isEmpty()){
            Edit_name.error = "Please Fill Name !"
            return
        }
        if (hp.isEmpty()){
            Edit_phone.error = "Please Fill Phone Number !"
            return
        }
        if (email.isEmpty()){
            Edit_email.error = "Please Fill Email !"
            return
        }
        if (password.isEmpty()){
            Edit_password.error = "Please Fill pssword !"
        }


        val akun = UserAccount(ktp,nama, hp, email, password)

        val regist = FirebaseDatabase.getInstance().getReference("akun")
        val check = FirebaseDatabase.getInstance().getReference("akun").orderByChild("ktp").equalTo(ktp)

        check.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){

                }else{
                    regist.child(ktp).setValue(akun).addOnCompleteListener{
                        intent = Intent (this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    Toast.makeText(this@RegisterActivity,"Successful Registration",Toast.LENGTH_LONG).show()
                }
            }
            override fun onCancelled(p0: DatabaseError){}
        })


    }
}
