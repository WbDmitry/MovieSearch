package com.wbdmitry.moviesearch

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wbdmitry.moviesearch.databinding.ActivityMainBinding
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment
import com.wbdmitry.moviesearch.ui.main.sittings.SettingsFragment

const val NAME_LOG_EVENT = "logEvent"
const val NAME_EVENT = "event"
const val NUMBER = 0

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
        initSetting()

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
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