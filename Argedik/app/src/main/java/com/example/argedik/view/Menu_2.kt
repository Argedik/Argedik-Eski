package com.example.argedik.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.argedik.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_fotograf_paylasma.*
import java.util.*

class Menu_2 : AppCompatActivity(){
    var secilenGorsel : Uri? = null
    var secilenBitmap : Bitmap? = null
    private lateinit var storage : FirebaseStorage
    private lateinit var auth:FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private val cameraRequest = 1888
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotograf_paylasma2)
        storage= FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()


    }

    fun gorselSec(view: View){
        title = "KotlinApp"
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)
        imageView = findViewById(R.id.imageView)
        val photoButton:ImageView = findViewById(R.id.imageView)
        photoButton.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 2)
        }
    }

    fun paylas(view:View){
        //depo işlemleri

        val uuid = UUID.randomUUID()
        val gorselIsmi = "${uuid}.jpg"
        val reference = storage.reference
        val gorselReference = reference.child("Resimler").child("Paylasim_Resimleri").child(gorselIsmi)

        if (secilenGorsel != null){
            //görseli telefondan firebase ye yükleme
            gorselReference.putFile(secilenGorsel!!).addOnSuccessListener {taskSnapshot->
                //görsel url alma
                val yuklenenGorselReference = FirebaseStorage.getInstance().reference.child("Resimler").child("Paylasim_Resimleri").child(gorselIsmi)
                yuklenenGorselReference.downloadUrl.addOnSuccessListener { asd->
                    val downloadUrl=asd.toString()
                    val guncelKullaniciEmail=auth.currentUser!!.email.toString()
                    val kullaniciYorumu=yorumText.text.toString()
                    val tarih = Timestamp.now()
                    //veri tabanı işlemleri

                    val postHashMap = hashMapOf<String, Any>()
                    postHashMap.put("gorselurl",downloadUrl)
                    postHashMap.put("kullaniciemail",guncelKullaniciEmail)
                    postHashMap.put("kullaniciyorum",kullaniciYorumu)
                    postHashMap.put("tarih",tarih)

                    database.collection("Post").add(postHashMap).addOnCompleteListener { task->
                        if (task.isSuccessful){
                            finish()
                        }
                    }.addOnFailureListener { exception->
                        Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { exception->
                    Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }else{
            println(gorselReference)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==cameraRequest){
            if (grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //izin verilince yapılcaklar
                val photoButton:ImageView = findViewById(R.id.imageView)
                photoButton.setOnClickListener {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, 2)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /*if (requestCode == 2) {

            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
            println("denemelik2 " + photo)
        }*/
        if(requestCode==2 && resultCode == Activity.RESULT_OK && data!= null){
            secilenGorsel=data.data
            println("denemelik2 " + secilenGorsel)
            if(secilenGorsel!=null){
                println("denemelik2 " + "nulldan cıktı mı")
                if(Build.VERSION.SDK_INT>=28){
                    val source= ImageDecoder.createSource(this.contentResolver,secilenGorsel!!)
                    secilenBitmap = ImageDecoder.decodeBitmap(source)
                    imageView.setImageBitmap(secilenBitmap)
                }else {
                    secilenBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, secilenGorsel)
                    imageView.setImageBitmap(secilenBitmap)
                }
            }else{
                println("ciddi")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}