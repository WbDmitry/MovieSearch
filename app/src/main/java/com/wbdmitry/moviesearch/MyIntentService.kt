package com.wbdmitry.moviesearch

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Intent
import java.io.File
import java.util.*

const val NAME_DIRECTORY = "data"
const val NAME_FILE = "Analytics.txt"

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        val event = intent?.getStringExtra(NAME_LOG_EVENT)

        @SuppressLint("SdCardPath")
        val path = getExternalFilesDir("")
        val letDirectory = File(path, NAME_DIRECTORY)
        letDirectory.mkdirs()
        val file = File(letDirectory, NAME_FILE)
        file.appendText(event + Date() + "\n")
    }
}