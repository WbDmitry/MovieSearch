package com.wbdmitry.moviesearch

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.wbdmitry.moviesearch.databinding.ActivityMainBinding
import com.wbdmitry.moviesearch.ui.main.history.HistoryFragment
import com.wbdmitry.moviesearch.ui.main.mapMovieTheaters.MapsMovieTheaterFragment
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment
import com.wbdmitry.moviesearch.ui.main.sittings.SettingsFragment

const val NAME_LOG_EVENT = "logEvent"
const val NAME_EVENT = "event"
const val NUMBER = 0
private const val REQUEST_CODE = 999

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val receiver = MainBroadcastReceiver()
    private val intentMyIntentService by lazy { Intent(this, MyIntentService::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState ?: run {
            openFragment(MovieListFragment.newInstance())
        }
        checkPermission()

        initSetting()

//        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        startEventLog(intentMyIntentService, getString(R.string.user_logged))

        binding.menuMovieList.setOnNavigationItemSelectedListener {
            clickMenuItem(it)
            true
        }
    }

    private fun saveSetting() {
        getPreferences(Context.MODE_PRIVATE)?.edit()
            ?.putBoolean(AppSetting.PREF_NAME, AppSetting.adultCheckBoxCondition)
            ?.apply()
    }

    private fun initSetting() {
        AppSetting.adultCheckBoxCondition = getPreferences(Context.MODE_PRIVATE)
            ?.getBoolean(AppSetting.PREF_NAME, false) ?: false
    }

    private fun clickMenuItem(item: MenuItem) {
        when (item.itemId) {
            R.id.setting_movie_list -> {
                openFragment(SettingsFragment.newInstance())
            }
            R.id.movie_list -> {
                openFragment(MovieListFragment.newInstance())
            }
            R.id.find_movie_theater -> {
                openFragment(MapsMovieTheaterFragment.newInstance())
            }
            R.id.history_movie_list -> {
                openFragment(HistoryFragment.newInstance())
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container,
                fragment
            ).commit()
    }

    private fun startEventLog(intent: Intent, event: String) {
        startService(
            intent.putExtra(NAME_EVENT, NUMBER).putExtra(
                NAME_LOG_EVENT,
                event
            )
        )
    }

    private fun checkPermission() {
        this.let {
            when {
                ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED -> {
                    getLocation()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    showRationaleDialog()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    private fun showRationaleDialog() {
        this.let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.alert_dialog_title))
                .setMessage(getString(R.string.alert_dialog_description))
                .setPositiveButton(getString(R.string.alert_rationable_dialog_positive_button)) { _, _ ->
                    requestPermission()
                }
                .setNegativeButton(getString(R.string.alert_rationable_dialog_negative_button)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE
        )
    }

    private fun showDialog(title: String, message: String) {
        this.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(getString(R.string.alert_dialog_negative_button)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkPermissionsResult(requestCode, grantResults)
    }

    private fun checkPermissionsResult(requestCode: Int, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                var grantedPermissions = 0
                if ((grantResults.isNotEmpty())) {
                    for (i in grantResults) {
                        if (i == PackageManager.PERMISSION_GRANTED) {
                            grantedPermissions++
                        }
                    }
                    if (grantResults.size == grantedPermissions) {
                        getLocation()
                    } else {
                        showDialog(
                            getString(R.string.alert_dialog_check_permissions_result_title),
                            getString(R.string.alert_dialog_check_permissions_result_description)
                        )
                    }
                } else {
                    showDialog(
                        getString(R.string.alert_dialog_check_permissions_result_title),
                        getString(R.string.alert_dialog_check_permissions_result_description)
                    )
                }
                return
            }
        }
    }

    override fun onStop() {
        saveSetting()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        startEventLog(intentMyIntentService, getString(R.string.user_exit))
    }
}