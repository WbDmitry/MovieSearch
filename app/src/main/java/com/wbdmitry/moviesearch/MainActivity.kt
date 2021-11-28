package com.wbdmitry.moviesearch

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wbdmitry.moviesearch.databinding.ActivityMainBinding
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment

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
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    MovieListFragment.newInstance()
                ).commit()
        }
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        startEventLog(intentMyIntentService, getString(R.string.user_logged))
    }

    private fun startEventLog(intent: Intent, event: String) {
        startService(
            intent.putExtra(NAME_EVENT, NUMBER).putExtra(
                NAME_LOG_EVENT,
                event
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        startEventLog(intentMyIntentService, getString(R.string.user_exit))
    }
}