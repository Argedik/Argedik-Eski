package com.example.argedik.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.argedik.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ImageView
import android.widget.EditText



class MainActivity : AppCompatActivity() {
    //fire base tanımlandı

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/

        //Daha önce güncel kullanıcı kontrolü
        auth = FirebaseAuth.getInstance()
        val guncelKullanici= auth.currentUser
        if(guncelKullanici!=null){
            val intent = Intent(this,Animation_Page::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun girisYap(view:View){
        //e-mail ile giriş işlemi
        if ((!emailText.text.isEmpty()) && (!parola.text.isEmpty()) ){
            auth.signInWithEmailAndPassword(emailText.text.toString(),parola.text.toString()).addOnCompleteListener { task->
                if(task.isSuccessful){
                    val guncelKullanici = auth.currentUser?.email.toString()
                    Toast.makeText(this, "Hoşgeldin: ${guncelKullanici}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Animation_Page::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception->
                Toast.makeText(this,exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }


    }
    //kayıt işemi
    fun kayitOl(view: View){
        if ((!emailText.text.isEmpty()) && (!parola.text.isEmpty())) {
            val email = emailText.text.toString()
            val sifre = parola.text.toString()
            auth.createUserWithEmailAndPassword(email, sifre).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, Menu_1::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}




