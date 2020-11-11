package com.example.argedik.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.argedik.R
import java.util.*

//import android.view.animation.Animation
//import android.view.animation.AnimationUtils


class Animation_Page : Activity() {
    var timer: Timer? = null
    //var bganim: Animation? = null
    var bgapp: ImageView? = null
    var kss_logo:ImageView?=null
    var animasyonlogo: LottieAnimationView?=null
    
    //val actionBar = supportActionBar

    /*override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()

        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility == 0) {

                //if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
            } else {
            }
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
*/

    /*
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
    */




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@Animation_Page, Menu_1::class.java)
                startActivity(intent)
                finish()
            }
        }, 1700)*/
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main2)
        val logo = logo()
        logo.start()


        //bganim = AnimationUtils.loadAnimation(this, R.anim.animation)
        // window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //actionBar?.hide()
        //actionBar!!.hide()

        //Handler().postDelayed(Runnable,5000)

        //val intent =Intent(applicationContext, Menu_1::class.java)
        //startActivity(intent)
        //finish()
    }

    private inner class logo : Thread() {
        override fun run() {
            try {
                bgapp = findViewById<View>(R.id.image) as ImageView
                kss_logo = findViewById<View>(R.id.app_logo) as ImageView
                animasyonlogo= findViewById(R.id.lottie) as LottieAnimationView

                animasyonlogo!!.animate().translationY(2800f).setDuration(6000).startDelay = 300
                bgapp!!.animate().translationY(-3200f).setDuration(2000).startDelay = 300
                kss_logo!!.animate().alpha(0f).setDuration(1000).startDelay = 600
                sleep(3200)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val intent = Intent(this@Animation_Page, AnaMenu::class.java)
            startActivity(intent)
            finish()
        }
    }
    //private val mHandler = Handler()
    /*fun startRepeating(v: View?) {
        Runnable.run()
    }
    fun stopRepeating(v: View?) {
        mHandler.removeCallbacks(Runnable)
    }*/
    /*private val Runnable: Runnable = object : Runnable {
        override fun run() {
            mHandler.postDelayed(this, 5000)
        }
    }*/
}



/*import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import com.example.argedik.R

class MainActivity2 : AppCompatActivity() {
    var bgapp: ImageView? = null
    var clover: ImageView? = null
    var emailText: EditText? = null
    var texthome: EditText? = null
    var menus: EditText? = null
    var bganim: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        bganim = AnimationUtils.loadAnimation(this, R.anim.animation)
        bgapp = findViewById<View>(R.id.image) as ImageView
        texthome = findViewById<View>(R.id.parola) as EditText
        emailText = findViewById<View>(R.id.emailText) as EditText

        bgapp!!.animate().translationY(-1900f).setDuration(800).startDelay = 300
        clover!!.animate().alpha(0f).setDuration(800).startDelay = 600
        emailText!!.animate().translationY(140f).alpha(0f).setDuration(800).startDelay = 300
        texthome!!.startAnimation(bganim)
        emailText!!.startAnimation(bganim)
    }*/


/*
import com.airbnb.lottie.LottieAnimationView
private var logo: ImageView? = null
private var bg: ImageView? = null
private var lottie: LottieAnimationView? = null
logo = findViewById(R.id.app_logo)
bg = findViewById(R.id.image)
lottie = findViewById(R.id.lottie)

bg!!.animate().translationY(-2200f).setDuration(1000).startDelay = 3000
lottie!!.animate().translationY(1700f).setDuration(1000).startDelay = 3500
logo!!.animate().translationY(1600f).setDuration(1000).startDelay = 3000*/