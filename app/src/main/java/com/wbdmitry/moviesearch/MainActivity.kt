package com.wbdmitry.moviesearch

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wbdmitry.moviesearch.databinding.ActivityMainBinding
import com.wbdmitry.moviesearch.ui.main.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val receiver = MainBroadcastReceiver()

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
    }
}