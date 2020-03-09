package su.vasic2000.darktheme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val TAG = "ToolbarActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        switchAndListener()
        fabAndListener()
    }

    private fun fabAndListener() {
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Fab was pressed", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun switchAndListener() {
        val switch: Switch = findViewById(R.id.MySwitch)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switch.isChecked = true
        }

        switch.setOnCheckedChangeListener { compoundButton: CompoundButton, _: Boolean ->
            if (compoundButton.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                restartApp()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                restartApp()
            }
        }
    }

    private fun initToolBar() {
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setLogo(R.mipmap.ic_launcher)
    }

    private fun setTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme)
        } else
            setTheme(R.style.AppTheme)
    }

    private fun restartApp() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            android.R.id.home -> {
                Log.d(TAG, "onOptionsItemSelected: ")
                onBackPressed()
                true
            }
            R.id.av_action_img_OK -> {
                Log.d(TAG, "onCheckItemSelected: ")
                Toast.makeText(this, "OK button pressed", Toast.LENGTH_LONG).show()
                true
            }
            R.id.av_action_img_Close -> {
                Log.d(TAG, "onCloseItemSelected: ")
                Toast.makeText(this, "Close button pressed", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
