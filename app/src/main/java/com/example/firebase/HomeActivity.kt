package com.example.firebase

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class  Providertype{
    BASIC,
    GOOGLE
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setup

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email?:"",provider?:"")

        //guardar los datos del usuario
        val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()
    }

    private fun setup(email:String, provider: String){
        title = "inicio"
        emailTextView.text = email
        provoderTextView.text = provider

        logOutButton.setOnClickListener{
            //borrar datos
            val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()


            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}