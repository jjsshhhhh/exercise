package com.example.exercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.app.Dialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.exercise.databinding.ActivityMainBinding
import com.example.exercise.databinding.FragmentMyPageBinding
import androidx.core.widget.addTextChangedListener
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

import com.example.exercise.HomeFragment
import com.example.exercise.DataFragment
import com.example.exercise.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity()  {

    private val fragmentManager = supportFragmentManager

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var homeFragment: HomeFragment? = null
    private var dataFragment: DataFragment? = null
    private var mypageFragment: MyPageFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        /*
        val namePreferences = getSharedPreferences("name_info", Context.MODE_PRIVATE)
        val nameInfo = namePreferences.getString("profile", "")
        val fragmentA = MyPageFragment()
        if (nameInfo != null) {
            fragmentA.changeName(nameInfo)
        }

        val bundleA = Bundle()
        bundleA.putString("bundle", nameInfo)
        val fragmentA = MyPageFragment()
        fragmentA.arguments = bundleA
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.profile_name_edit,fragmentA)
        transaction.commit()
         */
    }

    private fun initBottomNavigation() {
        homeFragment = HomeFragment()
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, homeFragment!!).commit()

        binding.navView.run{
        setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> {
                    if (homeFragment == null) {
                        homeFragment = HomeFragment()
                        fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, homeFragment!!).commit()
                    }
                    if (homeFragment != null) fragmentManager.beginTransaction().show(homeFragment!!).commit()
                    if (dataFragment != null) fragmentManager.beginTransaction().hide(dataFragment!!).commit()
                    if (mypageFragment != null) fragmentManager.beginTransaction().hide(mypageFragment!!).commit()

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_data -> {
                    if (dataFragment == null) {
                        dataFragment = DataFragment()
                        fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, dataFragment!!).commit()
                    }
                    if (homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment!!).commit()
                    if (dataFragment != null) fragmentManager.beginTransaction().show(dataFragment!!).commit()
                    if (mypageFragment != null) fragmentManager.beginTransaction().hide(mypageFragment!!).commit()

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_mypage -> {
                    if (mypageFragment == null) {
                        mypageFragment = MyPageFragment()
                        fragmentManager.beginTransaction().add(R.id.nav_host_fragment_activity_main, mypageFragment!!).commit()
                    }
                    if (homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment!!).commit()
                    if (dataFragment != null) fragmentManager.beginTransaction().hide(dataFragment!!).commit()
                    if (mypageFragment != null) fragmentManager.beginTransaction().show(mypageFragment!!).commit()

                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }}
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
/*
    fun initNavigationBar(){
        binding.navView.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.navigation_home -> setFragment(TAG_HOME_FRAGMENT, HomeFragment())
                    R.id.navigation_data -> setFragment(TAG_HOME_FRAGMENT, HomeFragment())
                    R.id.navigation_mypage -> setFragment(TAG_MYPAGE_FRAGMENT, MyPageFragment())
                }
                true
            }
        }
    }
 */
    fun receiveNameData(name: String) {
        getSharedPreferences("name_info", Context.MODE_PRIVATE).edit {
            putString("profile", name)
            apply()
        }
        Log.d("MainActivity", "저장 ${name}")
    }
    fun receiveAgeData(age: String) {
        getSharedPreferences("age_info", Context.MODE_PRIVATE).edit {
            putString("profile", age)
            apply()
        }
        Log.d("MainActivity", "저장 ${age}")
    }
    fun receiveHeightData(height: String) {
        getSharedPreferences("height_info", Context.MODE_PRIVATE).edit {
            putString("profile", height)
            apply()
        }
        Log.d("MainActivity", "저장 ${height}")
    }
    fun receiveWeightData(weight: String) {
        getSharedPreferences("weight_info", Context.MODE_PRIVATE).edit {
            putString("profile", weight)
            apply()
        }
        Log.d("MainActivity", "저장 ${weight}")
    }
}