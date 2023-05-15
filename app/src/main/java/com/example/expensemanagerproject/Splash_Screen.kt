package com.example.expensemanagerproject

import android.content.Intent
import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.expensemanagerproject.databinding.ActivitySplashScreenBinding

class Splash_Screen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)

        //version
        val manager = packageManager
        val info: PackageInfo = manager.getPackageInfo(
            packageName, 0
        )
        val version: String = info.versionName
        binding.txtversion.text = version
    }
}
