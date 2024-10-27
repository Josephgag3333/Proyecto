package com.example.proyecto

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import com.google.android.material.navigation.NavigationView

class NavigationActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer:DrawerLayout
    private lateinit var toggle:ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle=ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView:NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener (this)



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_item_inicio -> Toast.makeText(this,"Inicio",Toast.LENGTH_SHORT).show()
            R.id.nav_item_micuenta -> Toast.makeText(this,"MiCuenta",Toast.LENGTH_SHORT).show()
            R.id.nav_item_nosotros -> Toast.makeText(this,"Nosotros",Toast.LENGTH_SHORT).show()
            R.id.nav_item_contactanos -> Toast.makeText(this,"Contactanos",Toast.LENGTH_SHORT).show()
            R.id.nav_item_mipedido -> Toast.makeText(this,"MiPedido",Toast.LENGTH_SHORT).show()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}




