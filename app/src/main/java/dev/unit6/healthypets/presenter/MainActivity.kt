package dev.unit6.healthypets.presenter

import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
}
