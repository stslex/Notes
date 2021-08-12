package com.stslex93.notes

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.stslex93.notes.databinding.ActivityMainBinding
import com.stslex93.notes.ui.NoteViewModel
import com.stslex93.notes.utilites.appComponent
import dagger.Lazy
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private val noteViewModel: NoteViewModel by viewModels { viewModelFactory.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        applicationContext.appComponent.inject(this)
        setContentView(binding.root)
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