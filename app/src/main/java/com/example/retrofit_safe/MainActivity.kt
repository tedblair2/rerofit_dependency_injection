package com.example.retrofit_safe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragmentContainer))
    }

    override fun onSupportNavigateUp(): Boolean {
        val nav=findNavController(R.id.fragmentContainer).navigateUp()
        return nav || super.onSupportNavigateUp()
    }
}