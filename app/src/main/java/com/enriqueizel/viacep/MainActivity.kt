package com.enriqueizel.viacep

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enriqueizel.viacep.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    window.statusBarColor = Color.parseColor("#FF018786")
    val actionBar = supportActionBar
    actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF018786")))
  }
}