package com.example.exercise

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.exercise.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

import com.example.exercise.HomeFragment
import com.example.exercise.DataFragment
import com.example.exercise.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity()  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var dataFragment: DataFragment
    private lateinit var mypageFragment: MyPageFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationBar()

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun initNavigationBar(){
        binding.navView.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.navigation_home -> {
                        homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main, homeFragment)
                            .commit()

                    }
                    R.id.navigation_data -> {
                        dataFragment = DataFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main, dataFragment)
                            .commit()

                    }
                    R.id.navigation_mypage -> {
                        mypageFragment = MyPageFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main, mypageFragment)
                            .commit()

                    }
                }
                true
            }
        }
    }


}