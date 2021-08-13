package com.stslex93.notes

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.stslex93.notes.databinding.ActivityMainBinding
import com.stslex93.notes.utilites.initResources

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initResources()
        setSupportActionBar(binding.toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun popBackStack() {
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navHostFragment = (fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.popBackStack()
    }

}