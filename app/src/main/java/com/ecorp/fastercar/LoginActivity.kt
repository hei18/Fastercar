package com.ecorp.fastercar

import android.content.Intent
import android.app.AlertDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.ecorp.fastercar.ChildAtribute.UserAccount
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var Edit_email: EditText
    lateinit var Edit_password: EditText

    lateinit var userList: MutableList<UserAccount>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Edit_email = findViewById(R.id.Edit_email)
        Edit_password = findViewById(R.id.Edit_password)

        userList = mutableListOf()

        btn_login.setOnClickListener {
            doLogin()
        }
        toRegister.setOnClickListener {
            var intent = Intent (this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
//==============Back Pressed

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this@LoginActivity)
        builder.setTitle("Log Out")
        builder.setMessage("Are You Sure ?")
        builder.setPositiveButton("Yes"){dialog, which ->
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        builder.setNegativeButton("No"){dialog,which ->

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun doLogin(){

        val email = Edit_email.text.toString().trim()
        val password = Edit_password.text.toString().trim()
        val loading = ProgressDialog(this@LoginActivity)

        if (email.isEmpty()){
            Edit_email.error = "Please Fill Email !"
            return
        }
        if (password.isEmpty()){
            Edit_password.error = "Please Fill pssword !"
        }

        val myRef = FirebaseDatabase.getInstance().reference
        val ref_email = myRef.child("akun").orderByChild("email").equalTo(email)
        val ref_password = myRef.child("akun").orderByChild("password").equalTo(password)

        ref_email.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p1: DataSnapshot) {
                loading.setMessage("Please Wait...")
                loading.show()
                ref_password.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {
                        if (p1.exists() && p0.exists()){
                            userList.clear()
                            for (h in p0.children){
                                val usher = h.getValue(UserAccount::class.java)
                                userList.add(usher!!)
                                val k_ktp = usher.ktp

                                intent = Intent(this@LoginActivity, Dashboard::class.java)
                                intent.putExtra("ktp",k_ktp)
                                loading.dismiss()
                                startActivity(intent)
                            }
                        }else{
                            Toast.makeText(this@LoginActivity, "Email or Password not valid", Toast.LENGTH_LONG).show()
                            loading.dismiss()
                        }

                    }
                    override fun onCancelled(p0: DatabaseError){}
                })

            }
            override fun onCancelled(p1: DatabaseError){}
        })
        loading.dismiss()
    }


//EndOfCode

}
