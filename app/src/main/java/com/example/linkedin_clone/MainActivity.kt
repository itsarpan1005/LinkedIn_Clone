package com.example.linkedin_clone

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.linkedin_clone.databinding.ActivityMainBinding
import com.example.linkedin_clone.ui.Fragments.*
import com.example.linkedin_clone.ui.Profile
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    moveToFrag(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_network -> {
                    moveToFrag(NetworkFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_upload -> {
                    startActivity(Intent(this, AddPost::class.java))
                    return@OnNavigationItemSelectedListener false
                }
                R.id.nav_notification -> {
                    moveToFrag(NotificationFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_jobs -> {
                    moveToFrag(JobsFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = findViewById(R.id.container)
        val navView1 : NavigationView = findViewById(R.id.nav_view_menu)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)


        moveToFrag(HomeFragment())
        toggle =
            ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView1.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_account -> {
                    startActivity(Intent(this,Profile::class.java))
                }
                R.id.nav_logout -> {
                    Toast.makeText(this@MainActivity, "Second Item Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            true
        }
    }


override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (toggle.onOptionsItemSelected(item)) {
        true
    }
    return super.onOptionsItemSelected(item)
}



private fun moveToFrag(fragment: Fragment) {
    val fragmentTrans = supportFragmentManager.beginTransaction()
    fragmentTrans.replace(R.id.Fragment_container, fragment)
    fragmentTrans.commit()
}
}