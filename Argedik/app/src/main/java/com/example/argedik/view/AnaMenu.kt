package com.example.argedik.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.argedik.R

class AnaMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_menu)

    }
    fun fotograf(view:View){
        val intent = Intent(this@AnaMenu, Menu_2::class.java)
        startActivity(intent)
        finish()
    }
    fun galeri_ac(view:View){
        val intent = Intent(this@AnaMenu, Menu_1::class.java)
        startActivity(intent)
        finish()
    }
}