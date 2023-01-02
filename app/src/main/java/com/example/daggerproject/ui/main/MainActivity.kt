package com.example.daggerproject.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.daggerproject.R
import com.example.daggerproject.ui.BaseActivity
import com.example.daggerproject.ui.main.post.PostFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class MainActivity : BaseActivity(), OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        initNavigation()
    }

    private fun initNavigation() {
        val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this@MainActivity, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                sessionManager.logout()
                true
            }
            android.R.id.home -> {
                if (drawerLayout.isOpen) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                } else false

            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val navOption = NavOptions.Builder().setPopUpTo(R.id.main, true).build()
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileScreen, null, navOption)
            }
            R.id.nav_posts -> {
                if (isValidDestination(R.id.postScreen)) {
                    Navigation.findNavController(
                        this, R.id.nav_host_fragment
                    ).navigate(R.id.postScreen)
                }
            }
        }
        item.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return false
    }

    private fun isValidDestination(destination: Int): Boolean =
        destination != Navigation.findNavController(
            this, R.id.nav_host_fragment
        ).currentDestination?.id

}