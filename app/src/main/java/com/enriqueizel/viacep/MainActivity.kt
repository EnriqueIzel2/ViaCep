package com.enriqueizel.viacep

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.enriqueizel.viacep.api.Api
import com.enriqueizel.viacep.databinding.ActivityMainBinding
import com.enriqueizel.viacep.model.Endereco
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    window.statusBarColor = Color.parseColor("#FF018786")
    val actionBar = supportActionBar
    actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF018786")))

//    configura retrofit
    val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("viacep.com.br/ws/")
      .build()
      .create(Api::class.java)

    binding.btnSearchCep.setOnClickListener {
      val cep = binding.editCep.text.toString()

      if (cep.isEmpty()) {
        Toast.makeText(this, "Preencha o CEP", Toast.LENGTH_SHORT).show()
      } else {
        retrofit.getEndereco(cep).enqueue(object : Callback<Endereco> {
          override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
            if (response.code() == 200) {
              val logradouro = response.body()?.logradouro.toString()
              val bairro = response.body()?.bairro.toString()
              val localidade = response.body()?.localidade.toString()
              val uf = response.body()?.uf.toString()

              setFormularios(logradouro, bairro, localidade, uf)
            }
          }

          override fun onFailure(call: Call<Endereco>, t: Throwable) {
            Toast.makeText(applicationContext, "Erro inesperado!", Toast.LENGTH_SHORT).show()
          }
        })
      }
    }
  }

  private fun setFormularios(logradouro: String, bairro: String, localidade: String, uf: String) {
    binding.editLogradouro.setText(logradouro)
    binding.editBairro.setText(bairro)
    binding.editCidade.setText(localidade)
    binding.editEstado.setText(uf)
  }
}