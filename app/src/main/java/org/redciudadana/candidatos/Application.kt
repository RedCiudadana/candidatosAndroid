package org.redciudadana.candidatos

import androidx.multidex.MultiDexApplication
import com.twitter.sdk.android.core.Twitter
import org.redciudadana.candidatos.data.db.initializeDatabase
import org.redciudadana.candidatos.data.utils.fetchAll

class Application: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initializeDatabase(applicationContext)
        Twitter.initialize(this)
        fetchAll()
    }
}