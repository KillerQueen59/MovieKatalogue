package com.ojanbelajar.moviekatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ojanbelajar.moviekatalogue.databinding.ActivitySplashBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GlobalScope.launch {
            delay(1000L)
            startActivity(intentFor<HomeActivity>().noAnimation())
            finish()
        }
    }
}