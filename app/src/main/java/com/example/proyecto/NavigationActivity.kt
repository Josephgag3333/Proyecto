package com.example.proyecto
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Recuperar y mostrar los datos del usuario en el Navigation Drawer
        val headerView = navigationView.getHeaderView(0)
        val userNameTextView = headerView.findViewById<TextView>(R.id.nav_header_textView)
        val userEmailTextView = headerView.findViewById<TextView>(R.id.nav_header_textViewCorreo)

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "Usuario")
        val userEmail = sharedPreferences.getString("user_email", "email@example.com")

        userNameTextView.text = userName
        userEmailTextView.text = userEmail

        // Configurar navMenu
        navigation = findViewById(R.id.navMenu)
        navigation.setOnNavigationItemSelectedListener(mOnNavMenu)


        // Configurar el fragmento inicial al cargar la actividad
        supportFragmentManager.commit {
            replace<InicioFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

    private val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.itemFragment1 -> {
                supportFragmentManager.commit {
                    replace<InicioFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                true
            }
            R.id.itemFragment2 -> {
                supportFragmentManager.commit {
                    replace<RutinasFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                true
            }
            R.id.itemFragment3 -> {
                supportFragmentManager.commit {
                    replace<TiendaFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                true
            }
            else -> false
        }
    }
    // Comente por momento lo de menu.xml
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_inicio -> Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show()
            R.id.nav_item_micuenta -> Toast.makeText(this, "MiCuenta", Toast.LENGTH_SHORT).show()
            R.id.nav_item_nosotros -> Toast.makeText(this, "Nosotros", Toast.LENGTH_SHORT).show()
            R.id.nav_item_contactanos -> Toast.makeText(this, "Contactanos", Toast.LENGTH_SHORT).show()
            R.id.nav_item_mipedido -> Toast.makeText(this, "MiPedido", Toast.LENGTH_SHORT).show()
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
/* // Para cerrar sesion
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                // Lógica para cerrar sesión
                cerrarSesion()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cerrarSesion() {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


}