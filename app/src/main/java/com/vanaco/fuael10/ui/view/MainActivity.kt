package com.vanaco.fuael10.ui.view

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.vanaco.fuael10.databinding.ActivityMainBinding
import com.vanaco.fuael10.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread.sleep(650)
        screenSplash.setKeepOnScreenCondition{false}
        initLoadAds()

        quoteViewModel.onCreate()

        binding.btnCopy.setOnClickListener{
            val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("quote", binding.tvQuote.getText().toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Ya tenes tu frase copiada, compartila!", Toast.LENGTH_SHORT).show()
        }

        quoteViewModel.quoteModel.observe(this, Observer {
            binding.tvQuote.text = it.quote
            binding.tvDiego.text = it.diego
        })
        quoteViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        binding.viewContainer.setOnClickListener{quoteViewModel.randomQuote()}
    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }
}