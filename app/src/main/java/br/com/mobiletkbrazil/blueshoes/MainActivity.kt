package br.com.mobiletkbrazil.blueshoes

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity :
  AppCompatActivity(),
  NavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
      Snackbar
        .make(
          view,
          "Mude para a sua própria ação.",
          Snackbar.LENGTH_LONG
        )
        .setAction(
          "Ação",
          null
        )
        .show()
    }

    val toggle = ActionBarDrawerToggle(
      this,
      drawer_layout,
      toolbar,
      R.string.navigation_drawer_open,
      R.string.navigation_drawer_close
    )
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

    nav_view.setNavigationItemSelectedListener(this)
  }

  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    /**
     * Infla o menu. Adiciona itens a barra de topo, se
     * ela estiver presente.
     */
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    /**
     * Lidar com cliques de itens da barra de ação aqui.
     * A barra de ação manipulará automaticamente os
     * cliques no botão Home / Up, desde que seja
     * especificada uma atividade pai em AndroidManifest.xml.
     */
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    /**
     * Lida com cliques nos itens do menu gaveta.
     */
    when (item.itemId) {
      R.id.nav_camera -> {
      }
      R.id.nav_gallery -> {
      }
      R.id.nav_slideshow -> {
      }
      R.id.nav_manage -> {
      }
      R.id.nav_share -> {
      }
      R.id.nav_send -> {
      }
    }

    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }
}
